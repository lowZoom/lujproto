package luj.proto.maven.plugin.generate.dotproto.collect

import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.type.ClassOrInterfaceType
import groovy.transform.PackageScope

@PackageScope
class FieldImpl implements DotProtoCollector.Field {

  FieldImpl(MethodDeclaration fieldDeclaration) {
    _fieldDeclaration = fieldDeclaration
  }

  @Override
  String getName() {
    return _fieldDeclaration.nameAsString
  }

  @Override
  DotProtoCollector.FieldType getType() {
    ClassOrInterfaceType type = _fieldDeclaration.type.asClassOrInterfaceType()
    return new FieldTypeImpl(type, type.asClassOrInterfaceType().nameAsString)
  }

  private final MethodDeclaration _fieldDeclaration
}
