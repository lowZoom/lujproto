package luj.proto.maven.plugin.compile

import groovy.transform.PackageScope
import luj.proto.maven.plugin.compile.protoimpl.ProtobufGlueMaker

@PackageScope
final class ProtobufClassImpl implements ProtoListImpl.ProtobufClass {

  ProtobufClassImpl(String protoPath) {
    _protoPath = protoPath
  }

  @Override
  void generateGlue() {
    ProtobufGlueMaker.Factory.create(_protoPath).make()
  }

  private final String _protoPath
}
