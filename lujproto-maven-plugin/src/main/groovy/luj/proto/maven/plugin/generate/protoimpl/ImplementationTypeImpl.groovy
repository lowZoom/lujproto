package luj.proto.maven.plugin.generate.protoimpl

import groovy.transform.PackageScope

@PackageScope
class ImplementationTypeImpl implements ProtoImplGenerator.ImplementationType {

  ImplementationTypeImpl(String packageName, String typeName) {
    _packageName = packageName
    _typeName = typeName
  }

  @Override
  String getPackageName() {
    return _packageName
  }

  @Override
  String getTypeName() {
    return _typeName
  }

  private final String _packageName

  private final String _typeName
}
