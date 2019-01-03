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
    return _typeMap.getProtoType(_fieldDeclaration.type.asClassOrInterfaceType().nameAsString)
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
