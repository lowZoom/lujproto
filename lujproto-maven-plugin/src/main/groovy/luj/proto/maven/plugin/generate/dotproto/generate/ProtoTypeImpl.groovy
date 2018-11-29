package luj.proto.maven.plugin.generate.dotproto.generate

import com.github.javaparser.ast.body.TypeDeclaration
import groovy.transform.PackageScope

import java.nio.charset.StandardCharsets
import java.nio.file.Path

@PackageScope
class ProtoTypeImpl implements DotProtoFileGeneratorImpl.ProtoType {

  ProtoTypeImpl(TypeDeclaration declaration, String aPackage, Path protoPath) {
    _declaration = declaration

    _package = aPackage
    _protoPath = protoPath
  }

  @Override
  String getPackage() {
    return _package
  }

  @Override
  String getTypeName() {
    return _declaration.nameAsString
  }

  @Override
  List<DotProtoFileGeneratorImpl.ProtoField> getFieldList() {
    return _declaration.methods.collect { new ProtoFieldImpl(it, new TypeMapImpl()) }
  }

  @Override
  void writeProtoFile(String content) {
    new AntBuilder().mkdir(dir: _protoPath.parent.toString())
    _protoPath.write(content, StandardCharsets.UTF_8.name())
  }

  private final TypeDeclaration _declaration

  private final String _package
  private final Path _protoPath
}
