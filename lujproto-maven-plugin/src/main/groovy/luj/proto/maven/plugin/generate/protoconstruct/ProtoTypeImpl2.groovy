package luj.proto.maven.plugin.generate.protoconstruct

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.protoimpl.ProtoImplGenerator
import luj.proto.maven.plugin.generate.util.java.io.JavaClassSaver

import java.nio.file.Path

@PackageScope
class ProtoTypeImpl2 implements ProtoConstructGeneratorImpl.ProtoType, ProtoConstructGeneratorImpl.ClassSaver {

  ProtoTypeImpl2(DotProtoCollector.Proto proto,
                 ProtoImplGenerator.ImplementationType implementationType, ClassName stateType) {
    _proto = proto

    _implementationType = implementationType
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
  TypeName getImplementationType() {
    return ClassName.get(_implementationType.packageName, _implementationType.typeName)
  }

  @Override
  void makeStateConstructMethod(MethodSpec.Builder methodBuilder) {
    methodBuilder.addStatement('return $T.newBuilder()', _stateType.enclosingClassName())
  }

  @Override
  void saveInProtoPackage(TypeSpec constructType) {
    Path javaPath = _proto.dotProtoPath.resolveSibling(constructType.name + '.java')
    JavaClassSaver.Factory.create(javaPath, _proto.packageName, constructType).save()
  }

  private final DotProtoCollector.Proto _proto

  private final ProtoImplGenerator.ImplementationType _implementationType
  private final ClassName _stateType
}
