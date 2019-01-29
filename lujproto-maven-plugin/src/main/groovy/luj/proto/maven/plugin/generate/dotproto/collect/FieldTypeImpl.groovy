package luj.proto.maven.plugin.generate.dotproto.collect

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.type.Type
import groovy.transform.PackageScope

@PackageScope
class FieldTypeImpl implements DotProtoCollector.FieldType {

  FieldTypeImpl(Type type, String name) {
    _type = type
    _name = name
  }

  @Override
  CompilationUnit getCompilationUnit() {
    return _type.findCompilationUnit().orElseGet { assert false }
  }

  @Override
  String getName() {
    return _name
  }

  private final Type _type

  private final String _name
}
