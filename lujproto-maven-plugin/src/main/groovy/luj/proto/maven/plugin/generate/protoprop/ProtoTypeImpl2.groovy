package luj.proto.maven.plugin.generate.protoprop

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.util.java.io.JavaClassSaver

import java.nio.file.Path

@PackageScope
class ProtoTypeImpl2 implements ProtoPropGeneratorImpl.ProtoType, ProtoPropGeneratorImpl.ClassSaver {

  ProtoTypeImpl2(DotProtoCollector.Proto proto, ClassName stateType) {
    _proto = proto
    _stateType = stateType
  }

  @Override
  String getPackageName() {
    return _proto.packageName
  }

  @Override
  String getProtoName() {
    return _proto.protoName
  }

  @Override
  List<ProtoPropGeneratorImpl.ProtoField> getFieldList() {
    return _proto.fieldList.collect { new ProtoFieldImpl2(it) }
  }

  @Override
  TypeName getStateType() {
    return _stateType
  }

  @Override
  void saveInProtoPackage(TypeSpec listType) {
    Path javaPath = _proto.dotProtoPath.resolveSibling(listType.name + '.java')
    JavaClassSaver.Factory.create(javaPath, _proto.packageName, listType).save()
  }

  private final DotProtoCollector.Proto _proto

  private final ClassName _stateType
}
