package luj.proto.maven.plugin.generate

import com.github.javaparser.ast.body.TypeDeclaration
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.util.maven.MavenHelper

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
    //TODO: 等待实现
  }

  @Override
  TypeDeclaration getDeclaration() {
    return _declaration
  }

  private final TypeDeclaration _declaration

  private final MavenHelper _maven
}
