package luj.proto.maven.plugin.generate.dotproto.collect

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.type.ClassOrInterfaceType
import groovy.transform.PackageScope

@PackageScope
class FieldTypeImpl implements DotProtoCollector.FieldType {

  FieldTypeImpl(ClassOrInterfaceType type, String name) {
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

  @Override
  DotProtoCollector.FieldType getTypeParam(int index) {
    ClassOrInterfaceType param = _type.getTypeArguments()
        .orElse([])[index]
        .asClassOrInterfaceType()

    return new FieldTypeImpl(param, param.nameAsString)
  }

  private final ClassOrInterfaceType _type

  private final String _name
}
