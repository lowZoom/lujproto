package luj.proto.maven.plugin.generate.protoprop

import com.github.javaparser.ast.body.MethodDeclaration
import groovy.transform.PackageScope

@PackageScope
class ProtoFieldImpl implements ProtoPropGeneratorImpl.ProtoField {

  ProtoFieldImpl(MethodDeclaration declaration) {
    _declaration = declaration
  }

  @Override
  String getName() {
    return _declaration.nameAsString
  }

  private final MethodDeclaration _declaration
}
