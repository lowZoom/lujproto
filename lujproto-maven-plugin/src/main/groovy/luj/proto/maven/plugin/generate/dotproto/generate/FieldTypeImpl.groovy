package luj.proto.maven.plugin.generate.dotproto.generate

import groovy.transform.PackageScope

@PackageScope
class FieldTypeImpl implements DotProtoFileGeneratorImpl.FieldType {

  FieldTypeImpl(String protoPath, String name) {
    _protoPath = protoPath
    _name = name
  }

  @Override
  String getProtoPath() {
    return _protoPath
  }

  @Override
  String getName() {
    return _name
  }

  private final String _protoPath

  private final String _name
}
