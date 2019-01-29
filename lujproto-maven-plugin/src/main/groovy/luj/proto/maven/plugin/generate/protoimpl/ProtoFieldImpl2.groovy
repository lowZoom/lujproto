package luj.proto.maven.plugin.generate.protoimpl

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.util.java.parse.TypeFullNameResolver

@PackageScope
class ProtoFieldImpl2 implements ProtoImplGeneratorImpl.ProtoField {

  ProtoFieldImpl2(DotProtoCollector.Field field, Map<String, DotProtoCollector.Proto> protoMap) {
    _field = field
    _protoMap = protoMap
  }

  @Override
  String getName() {
    return _field.name
  }

  @Override
  TypeName getType() {
    String fullName = TypeFullNameResolver.Factory.create(_field.type, _protoMap).resolve()
    int sep = fullName.lastIndexOf('.')
    return ClassName.get(fullName.substring(0, sep), fullName.substring(sep + 1))
  }

  private final DotProtoCollector.Field _field

  private final Map<String, DotProtoCollector.Proto> _protoMap
}
