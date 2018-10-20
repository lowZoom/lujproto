package luj.proto.maven.plugin.compile

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class ProtoListImpl implements ProtoCompilerImpl.ProtoList {

  @Override
  boolean isEmpty() {
    return !_protoList
  }

  @Override
  void compileTo(String outputDir) {
    OutputRoot outputRoot = _project.getOutputRoot(outputDir)

    //TODO: 拿到protobuf的版本
    String protobufVer = _project.protobufVersion
    EnvDir envDir = outputRoot.getEnvDir(protobufVer)

    //TODO: 搜索protoc的路径，没有就下载
    InstalledProtoc protoc = envDir.findProtoc() ?: envDir.installProtoc('protoc-')

    for (proto in _protoList) {
      protoc.compile(proto)
    }

//    String outPath = Paths.get(_generateRoot, outputDir)
//    for (proto in _fileList) {
//      proto.compileTo(outPath)
//    }

    //TODO: 把生成的文件加入编译路径
    outputRoot.addToProjectCompile()
  }

  interface Project {

    OutputRoot getOutputRoot(String outputDir)

    String getProtobufVersion()
  }

  interface OutputRoot {

    EnvDir getEnvDir(String protobufVersion)

    void addToProjectCompile()
  }

  interface EnvDir {

    InstalledProtoc findProtoc()

    InstalledProtoc installProtoc(String tempDir)
  }

  interface InstalledProtoc {

    void compile(String protoPath)
  }

  private List<String> _protoList

  private Project _project
}
