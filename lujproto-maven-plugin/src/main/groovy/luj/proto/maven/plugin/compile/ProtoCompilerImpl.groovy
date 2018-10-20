package luj.proto.maven.plugin.compile

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class ProtoCompilerImpl implements ProtoCompiler {

  @Override
  void compile() {
    //TODO: 搜集所有的proto文件
    ProtoList protoList = _projectRoot.findProto()
    if (protoList.empty) {
      return
    }

    //TODO: 编译生成搜到的proto文件
    protoList.compileTo('lujproto')
  }

  interface ProjectRoot {

    ProtoList findProto()
  }

  interface ProtoList {

    boolean isEmpty()

    void compileTo(String outputDir)
  }

  private ProjectRoot _projectRoot
}
