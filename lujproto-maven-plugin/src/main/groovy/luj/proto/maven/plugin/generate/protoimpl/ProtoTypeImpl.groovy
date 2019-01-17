package luj.proto.maven.plugin.generate.protoimpl

import com.github.javaparser.ast.body.TypeDeclaration
import com.google.common.io.Files
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.util.java.io.JavaClassSaver

import java.nio.file.Path

@PackageScope
class ProtoTypeImpl implements ProtoImplGeneratorImpl.ProtoType, ProtoImplGeneratorImpl.ClassSaver {

  ProtoTypeImpl(Path dotProtoPath, TypeDeclaration declaration, String packageName) {
    _dotProtoPath = dotProtoPath

    _declaration = declaration
    _packageName = packageName
  }

  @Override
  String getTypeName() {
    return Files.getNameWithoutExtension(_dotProtoPath.toString())
  }

  @Override
  String getPackageName() {
    return _packageName
  }

  @Override
  List<ProtoImplGeneratorImpl.ProtoField> getFieldList() {
    return _declaration.getMethods()
        .collect { new ProtoFieldImpl(it) }
  }

  @Override
  void saveInProtoPackage(TypeSpec implType) {
    Path javaPath = _dotProtoPath.resolveSibling(implType.name + '.java')
    JavaClassSaver.Factory.create(javaPath, _packageName, implType).save()
  }

  private final Path _dotProtoPath

  private final TypeDeclaration _declaration
  private final String _packageName
}
