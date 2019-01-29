package luj.proto.maven.plugin.generate.util.java.parse

import com.github.javaparser.ast.ImportDeclaration
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector

@PackageScope
class TypeFullNameResolverImpl2 implements TypeFullNameResolver {

  TypeFullNameResolverImpl2(DotProtoCollector.FieldType type, Map<String, DotProtoCollector.Proto> protoMap) {
    _type = type
    _protoMap = protoMap
  }

  @Override
  String resolve() {
    return findInImport() ?: findInProtoPackage() ?: _type.name
  }

  private String findInImport() {
    return _type.compilationUnit.imports
        .collect { ImportDeclaration i -> i.name }
        .findAll { it.identifier == _type.name }
        .collect { it.asString() }
        .find { it }
  }

  private String findInProtoPackage() {
    String fullName = _type.compilationUnit.packageDeclaration
        .map { it.nameAsString + '.' }
        .orElse('') + _type.name

    return (fullName in _protoMap) ? fullName : null
  }

  private final DotProtoCollector.FieldType _type

  private final Map<String, DotProtoCollector.Proto> _protoMap
}
