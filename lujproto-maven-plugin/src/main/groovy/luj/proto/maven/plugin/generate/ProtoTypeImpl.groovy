package luj.proto.maven.plugin.generate

import com.github.javaparser.ast.body.TypeDeclaration
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.generate.DotProtoFileGenerator
import luj.proto.maven.plugin.generate.maven.MavenHelper

@PackageScope
class ProtoTypeImpl implements ProtoAllGeneratorImpl.ProtoType {

  ProtoTypeImpl(TypeDeclaration declaration, MavenHelper maven) {
    _declaration = declaration
    _maven = maven
  }

  @Override
  boolean isInterface() {
    return _declaration.toClassOrInterfaceDeclaration()
        .map { it.isInterface() }
        .orElse(false)
  }

  @Override
  void logWrongType() {

  }

  @Override
  void generateAll() {
    DotProtoFileGenerator.Factory.create(_declaration, _maven).generate()


  }

  private final TypeDeclaration _declaration

  private final MavenHelper _maven
}
