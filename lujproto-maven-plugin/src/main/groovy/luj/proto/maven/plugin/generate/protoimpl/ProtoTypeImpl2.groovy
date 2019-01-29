package luj.proto.maven.plugin.generate.protoimpl

import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.util.java.io.JavaClassSaver

import java.nio.file.Path

@PackageScope
class ProtoTypeImpl2 implements ProtoImplGeneratorImpl.ProtoType, ProtoImplGeneratorImpl.ClassSaver {

  ProtoTypeImpl2(DotProtoCollector.Proto proto, Map<String, DotProtoCollector.Proto> protoMap) {
    _proto = proto
    _protoMap = protoMap
  }

  @Override
  String getTypeName() {
    return _proto.protoName
  }

  @Override
  String getPackageName() {
    return _proto.packageName
  }

  @Override
  List<ProtoImplGeneratorImpl.ProtoField> getFieldList() {
    return _proto.fieldList.collect { new ProtoFieldImpl2(it, _protoMap) }
  }

  @Override
  void saveInProtoPackage(TypeSpec implType) {
    Path javaPath = _proto.dotProtoPath.resolveSibling(implType.name + '.java')
    JavaClassSaver.Factory.create(javaPath, _proto.packageName, implType).save()
  }

  private final DotProtoCollector.Proto _proto

  private final Map<String, DotProtoCollector.Proto> _protoMap
}
