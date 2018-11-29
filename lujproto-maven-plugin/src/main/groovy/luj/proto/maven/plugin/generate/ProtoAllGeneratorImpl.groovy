package luj.proto.maven.plugin.generate

import groovy.transform.PackageScope

import java.nio.file.Path

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

    // 每个协议开始生成对应文件
    _sourceRoot.generateAll(protoList)
  }

  interface SourceRoot {

    List<ProtoType> searchProto()

    void logEmpty()

    void generateAll(List<ProtoType> protoList)
  }

  interface ProtoType {

    boolean isInterface()

    void logWrongType()

    void generateAll(Path protocPath)
  }

  private final SourceRoot _sourceRoot
}
