package luj.proto.maven.plugin.generate.dotproto

import groovy.transform.PackageScope

@PackageScope
class DotProtoFileGeneratorImpl implements DotProtoFileGenerator {

  DotProtoFileGeneratorImpl(ProtoType protoType) {
    _protoType = protoType
  }

  @Override
  void generate() {
    _protoType.writeProtoFile("syntax=")
  }

  interface ProtoType {

    void writeProtoFile(String content)
  }

  private final ProtoType _protoType
}
