package luj.proto.maven.plugin.generate.protoimpl

import com.github.javaparser.ast.ImportDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.type.Type
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName
import groovy.transform.PackageScope

@PackageScope
class ProtoFieldImpl implements ProtoImplGeneratorImpl.ProtoField {

  ProtoFieldImpl(MethodDeclaration fieldMethod) {
    _fieldMethod = fieldMethod
  }

  @Override
  String getName() {
    return _fieldMethod.nameAsString
  }

  @Override
  TypeName getType() {
    Type fieldType = _fieldMethod.type
    return ClassName.get(getPackage(fieldType), fieldType.toString())
  }

  private String getPackage(Type type) {
    return type.findCompilationUnit()
        .map { it.imports }
        .orElseGet { [] }
        .collect { ImportDeclaration d -> d.nameAsString }
        .findAll { it.endsWith('.' + type.toString()) }
        .collect { it.substring(0, it.lastIndexOf('.')) }
        .withDefault { '' }[0]
  }

  private final MethodDeclaration _fieldMethod
}
