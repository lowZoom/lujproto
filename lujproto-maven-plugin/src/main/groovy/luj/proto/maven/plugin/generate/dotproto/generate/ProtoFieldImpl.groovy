package luj.proto.maven.plugin.generate.dotproto.generate

import com.github.javaparser.ast.body.MethodDeclaration
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.util.java.parse.TypeFullNameResolver

@PackageScope
class ProtoFieldImpl implements DotProtoFileGeneratorImpl.ProtoField {

  ProtoFieldImpl(MethodDeclaration fieldDeclaration, TypeMap scalarTypeMap) {
    _fieldDeclaration = fieldDeclaration
    _scalarTypeMap = scalarTypeMap
  }

  @Override
  DotProtoFileGeneratorImpl.FieldType getType() {
    String fieldType = TypeFullNameResolver.Factory.create(_fieldDeclaration.type).resolve()
    String scalarType = _scalarTypeMap.getProtoType(fieldType)

    if (scalarType) {
      return new FieldTypeImpl(null, scalarType)
    }
    return toObjectType(fieldType)
  }

  @Override
  String getName() {
    return _fieldDeclaration.name
  }

  private FieldTypeImpl toObjectType(String typeName) {
    String protoPath = typeName.replace('.', '/') + '.proto'
    return new FieldTypeImpl(protoPath, typeName)
  }

  interface TypeMap {

    String getProtoType(String javaType)
  }

  private final MethodDeclaration _fieldDeclaration

  private final TypeMap _scalarTypeMap
//  private final Map<String, DotProtoCollector.Proto> _linkTypeMap
}
