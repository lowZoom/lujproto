package luj.proto.maven.plugin.compile.protoimpl

import groovy.transform.PackageScope

@PackageScope
final class ProtobufGlueMakerImpl implements ProtobufGlueMaker {

  ProtobufGlueMakerImpl(OutputPackage outputPackage) {
    _outputPackage = outputPackage
  }

  @Override
  void make() {
    _outputPackage.generateBuilderImpl("生成 -> ${_outputPackage}BuilderImpl")
  }

  interface OutputPackage {

    void generateBuilderImpl(String implName)
  }

  interface ProtoFile {

//    void generateBuilderImpl()

    String getProtoName()
  }

  private final String _protoPath

  private final OutputPackage _outputPackage
}
