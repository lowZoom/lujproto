package luj.proto.maven.plugin.generate.dotproto.generate

import com.github.javaparser.ast.body.MethodDeclaration
import groovy.transform.PackageScope

@PackageScope
class ProtoFieldImpl implements DotProtoFileGeneratorImpl.ProtoField {

  ProtoFieldImpl(MethodDeclaration fieldDeclaration, TypeMap scalarTypeMap) {
    _fieldDeclaration = fieldDeclaration
    _scalarTypeMap = scalarTypeMap
  }

  @Override
  DotProtoFileGeneratorImpl.FieldType getType() {
    String fieldType = _fieldDeclaration.type.asClassOrInterfaceType().nameAsString
    String scalarType = _scalarTypeMap.getProtoType(fieldType)

    //TODO: 自定义类型要取出proto路径
    assert scalarType: "${fieldType}"

    return new FieldTypeImpl(null, scalarType)
  }

  @Override
  String getName() {
    return _fieldDeclaration.name
  }

  interface TypeMap {

    String getProtoType(String javaType)
  }

  private final MethodDeclaration _fieldDeclaration

  private final TypeMap _scalarTypeMap
//  private final Map<String, DotProtoCollector.Proto> _linkTypeMap
}
