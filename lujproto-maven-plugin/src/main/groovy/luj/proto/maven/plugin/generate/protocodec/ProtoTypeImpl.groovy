package luj.proto.maven.plugin.generate.protocodec

import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.util.java.io.JavaClassSaver

import java.nio.file.Path

@PackageScope
class ProtoTypeImpl implements ProtoCodecGeneratorImpl.ProtoType {

  ProtoTypeImpl(DotProtoCollector.Proto proto) {
    _proto = proto
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
  void saveInSamePackage(TypeSpec type) {
    Path javaPath = _proto.dotProtoPath.resolveSibling(type.name + '.java')
    JavaClassSaver.Factory.create(javaPath, _proto.packageName, type).save()
  }

  private final DotProtoCollector.Proto _proto
}
