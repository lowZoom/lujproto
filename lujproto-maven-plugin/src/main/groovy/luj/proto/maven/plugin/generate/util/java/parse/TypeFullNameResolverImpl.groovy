package luj.proto.maven.plugin.generate.util.java.parse

import com.github.javaparser.ast.ImportDeclaration
import com.github.javaparser.ast.type.Type
import groovy.transform.PackageScope

@PackageScope
class TypeFullNameResolverImpl implements TypeFullNameResolver {

  TypeFullNameResolverImpl(Type type) {
    _type = type
  }

  @Override
  String resolve() {
    String simpleName = _type.asClassOrInterfaceType().nameAsString
    return findInImport(simpleName) ?: makeFullName(simpleName)
  }

  private String findInImport(String simpleName) {
    return _type.findCompilationUnit()
        .map { it.imports }
        .orElseGet { [] }
        .collect { ImportDeclaration i -> i.name }
        .findAll { it.identifier == simpleName }
        .collect { it.asString() }
        .find { it }
  }

  private String makeFullName(String simpleName) {
    return _type.findCompilationUnit()
        .flatMap { it.packageDeclaration }
        .map { it.nameAsString + '.' }
        .orElse('') + simpleName
  }

  private final Type _type
}
