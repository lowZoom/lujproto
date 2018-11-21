package luj.proto.maven.plugin.compile

import groovy.transform.PackageScope
import luj.proto.maven.plugin.compile.protoimpl.ProtobufGlueMaker

@PackageScope
final class ProtobufClassImpl implements ProtoListImpl.ProtobufClass {

  @Override
  void generateGlue() {
    ProtobufGlueMaker.Factory.create().make()
  }
}
