package luj.proto.maven.plugin.generate.protoprop

import com.github.javaparser.ast.body.TypeDeclaration
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.util.java.JavaClassSaver

import java.nio.file.Path

@PackageScope
class ProtoTypeImpl implements ProtoPropGeneratorImpl.ProtoType, ProtoPropGeneratorImpl.ClassSaver {

  ProtoTypeImpl(Path dotProtoPath, TypeDeclaration declaration, String packageName, ClassName stateType) {
    _dotProtoPath = dotProtoPath

    _declaration = declaration
    _packageName = packageName

    _stateType = stateType
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
  List<ProtoPropGeneratorImpl.ProtoField> getFieldList() {
    return _declaration.methods.collect { new ProtoFieldImpl(it) }
  }

  @Override
  TypeName getStateType() {
    return _stateType
  }

  @Override
  void saveInProtoPackage(TypeSpec listType) {
    Path javaPath = _dotProtoPath.resolveSibling(listType.name + '.java')
    JavaClassSaver.Factory.create(javaPath, _packageName, listType).save()
  }

  private final Path _dotProtoPath

  private final TypeDeclaration _declaration
  private final String _packageName

  private final ClassName _stateType
}
