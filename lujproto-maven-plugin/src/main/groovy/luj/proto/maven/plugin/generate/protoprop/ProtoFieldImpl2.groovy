package luj.proto.maven.plugin.generate.protoprop

import com.squareup.javapoet.ClassName
import groovy.transform.PackageScope
import luj.data.type.JRef
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.util.java.parse.TypeFullNameResolver

@PackageScope
class ProtoFieldImpl2 implements ProtoPropGeneratorImpl.ProtoField {

  ProtoFieldImpl2(DotProtoCollector.Field field, ClassName stateType) {
    _field = field
    _stateType = stateType
  }

  @Override
  String getName() {
    return _field.name
  }

  @Override
  String getStateGetter() {
    String stateTypeName = _stateType.simpleNames().join('.')
    String typeName = TypeFullNameResolver.Factory.create(_field.type, [:]).resolve()

    String postfix = (typeName != JRef.name) ? '' : 'Builder'
    return "$stateTypeName::get${_field.name.capitalize()}$postfix"
  }

  private final DotProtoCollector.Field _field

  private final ClassName _stateType
}
