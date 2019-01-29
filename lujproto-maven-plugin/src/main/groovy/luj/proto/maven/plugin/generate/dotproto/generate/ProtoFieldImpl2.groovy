package luj.proto.maven.plugin.generate.dotproto.generate

import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.util.java.parse.TypeFullNameResolver

@PackageScope
class ProtoFieldImpl2 implements DotProtoFileGeneratorImpl.ProtoField {

  ProtoFieldImpl2(DotProtoCollector.Field field, TypeMap scalarTypeMap, Map<String, DotProtoCollector.Proto> protoMap) {
    _field = field

    _scalarTypeMap = scalarTypeMap
    _protoMap = protoMap
  }

  @Override
  DotProtoFileGeneratorImpl.FieldType getType() {
    String fieldType = TypeFullNameResolver.Factory.create(_field.type, _protoMap).resolve()
    String scalarType = _scalarTypeMap.getProtoType(fieldType)

    if (scalarType) {
      return new FieldTypeImpl(null, scalarType)
    }
    return toObjectType(fieldType)
  }

  @Override
  String getName() {
    return _field.getName()
  }

  private FieldTypeImpl toObjectType(String typeName) {
    String protoPath = typeName.replace('.', '/') + '.proto'
    return new FieldTypeImpl(protoPath, typeName)
  }

  interface TypeMap {

    String getProtoType(String javaType)
  }

  private final DotProtoCollector.Field _field

  private final TypeMap _scalarTypeMap
  private final Map<String, DotProtoCollector.Proto> _protoMap
}
