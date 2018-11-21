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

    // 拿到protobuf的版本
    String protobufVer = _project.protobufVersion
    EnvDir envDir = outputRoot.getEnvDir(protobufVer)

    // 搜索protoc的路径，没有就下载
    Protoc protoc = envDir.findProtoc() ?: envDir.installProtoc('protoc-')

    // 生成java代码
    _protoList
        .collect { protoc.compile(it) }
        .forEach { it.generateGlue() }

    // 把生成的文件加入编译路径
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

    Protoc findProtoc()

    Protoc installProtoc(String tempDir)
  }

  interface Protoc {

    ProtobufClass compile(String protoPath)
  }

  interface ProtobufClass {

    void generateGlue()
  }

  private List<String> _protoList

  private Project _project
}
