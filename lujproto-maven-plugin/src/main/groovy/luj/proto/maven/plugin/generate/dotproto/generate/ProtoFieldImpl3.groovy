package luj.proto.maven.plugin.generate.dotproto.generate

import groovy.transform.PackageScope
import luj.data.type.JRef
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.util.java.parse.TypeFullNameResolver

@PackageScope
class ProtoFieldImpl3 implements DotProtoFileGeneratorImpl.ProtoField {

  ProtoFieldImpl3(DotProtoCollector.Field field, TypeMap scalarTypeMap, Map<String, DotProtoCollector.Proto> protoMap) {
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

    if (fieldType == JRef.name) {
      DotProtoCollector.FieldType paramType = _field.type.getTypeParam(0)
      return toObjectType(TypeFullNameResolver.Factory.create(paramType, _protoMap).resolve())
    }

    throw new UnsupportedOperationException("不支持的字段类型：$fieldType")
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
