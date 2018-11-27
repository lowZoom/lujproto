package luj.proto.maven.plugin.generate

import groovy.transform.PackageScope

@PackageScope
class ProtoAllGeneratorImpl implements ProtoAllGenerator {

  ProtoAllGeneratorImpl(SourceRoot sourceRoot) {
    _sourceRoot = sourceRoot
  }

  @Override
  void generate() {
    // 扫描收集所有@Proto的接口
    List<ProtoType> protoList = _sourceRoot.searchProto()

    if (!protoList) {
      // 如果什么都没找到，中断
      _sourceRoot.logEmpty()
      return
    }

    // 如果有非接口，报错，中断
    List<ProtoType> wrongList = protoList.findAll { !it.isInterface() }
    if (wrongList) {
      wrongList.each { it.logWrongType() }
      return
    }

    // 部署好protoc
    _sourceRoot.prepareGenerate()

    // 每个协议开始生成对应文件
    protoList.each { it.generateAll() }
  }

  interface SourceRoot {

    List<ProtoType> searchProto()

    void logEmpty()

    void prepareGenerate()
  }

  interface ProtoType {

    boolean isInterface()

    void logWrongType()

    void generateAll()
  }

  private final SourceRoot _sourceRoot
}
