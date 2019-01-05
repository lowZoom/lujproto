package luj.proto.maven.plugin.generate.dotproto.generate

import com.github.javaparser.ast.body.MethodDeclaration
import groovy.transform.PackageScope

@PackageScope
class ProtoFieldImpl implements DotProtoFileGeneratorImpl.ProtoField {

  ProtoFieldImpl(MethodDeclaration fieldDeclaration, TypeMap typeMap) {
    _fieldDeclaration = fieldDeclaration
    _typeMap = typeMap
  }

  @Override
  String getType() {
    String fieldType = _fieldDeclaration.type.asClassOrInterfaceType().nameAsString
    String scalarType = _typeMap.getProtoType(fieldType)
    assert scalarType
    return scalarType
  }

  @Override
  String getName() {
    return _fieldDeclaration.name
  }

  interface TypeMap {

    String getProtoType(String javaType)
  }

  private final MethodDeclaration _fieldDeclaration

  private final TypeMap _typeMap
}
