package luj.proto.maven.plugin.generate.protoprop

import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector

@PackageScope
class ProtoFieldImpl2 implements ProtoPropGeneratorImpl.ProtoField {

  ProtoFieldImpl2(DotProtoCollector.Field field) {
    _field = field
  }

  @Override
  String getName() {
    return _field.name
  }

  private final DotProtoCollector.Field _field
}
