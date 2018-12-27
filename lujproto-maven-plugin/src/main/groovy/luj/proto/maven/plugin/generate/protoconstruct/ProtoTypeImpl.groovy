package luj.proto.maven.plugin.generate.protoconstruct

import com.github.javaparser.ast.body.TypeDeclaration
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.protoimpl.ProtoImplGenerator
import luj.proto.maven.plugin.generate.util.java.JavaClassSaver

import java.nio.file.Path

@PackageScope
class ProtoTypeImpl implements ProtoConstructGeneratorImpl.ProtoType, ProtoConstructGeneratorImpl.ClassSaver {

  ProtoTypeImpl(Path dotProtoPath, TypeDeclaration declaration, String packageName,
                ProtoImplGenerator.ImplementationType implementationType) {
    _dotProtoPath = dotProtoPath

    _declaration = declaration
    _packageName = packageName

    _implementationType = implementationType
  }

  @Override
  String getPackageName() {
    return _packageName
  }

  @Override
  String getProtoName() {
    return _declaration.nameAsString
  }

  @Override
  TypeName getImplementationType() {
    return ClassName.get(_implementationType.packageName, _implementationType.typeName)
  }

  @Override
  void saveInProtoPackage(TypeSpec constructType) {
    Path javaPath = _dotProtoPath.resolveSibling(constructType.name + '.java')
    JavaClassSaver.Factory.create(javaPath, _packageName, constructType).save()
  }

  private final Path _dotProtoPath

  private final TypeDeclaration _declaration
  private final String _packageName

  private final ProtoImplGenerator.ImplementationType _implementationType
}
