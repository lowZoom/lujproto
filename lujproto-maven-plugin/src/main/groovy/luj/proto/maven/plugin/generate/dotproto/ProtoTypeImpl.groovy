package luj.proto.maven.plugin.generate.dotproto

import com.github.javaparser.ast.body.TypeDeclaration
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.maven.MavenHelper

import java.nio.charset.StandardCharsets
import java.nio.file.Path

@PackageScope
class ProtoTypeImpl implements DotProtoFileGeneratorImpl.ProtoType {

  ProtoTypeImpl(TypeDeclaration declaration, MavenHelper maven) {
    _declaration = declaration
    _maven = maven
  }

  @Override
  void writeProtoFile(String content) {
    makeProtoDir()
        .resolve("${_declaration.name}.proto")
        .write(content, StandardCharsets.UTF_8.name())
  }

  private Path makeProtoDir() {
    String packagePath = splitPackage().join(File.separator)
    Path dirPath = _maven.path.targetGeneratedsourcesLujproto.resolve(packagePath)
    new AntBuilder().mkdir(dir: dirPath.toString())
    return dirPath
  }

  private List splitPackage() {
    return _declaration.findCompilationUnit()
        .flatMap { it.packageDeclaration }
        .map { it.nameAsString }
        .map { it.split(/\./) }
        .orElseGet { [] }
  }

  private final TypeDeclaration _declaration

  private final MavenHelper _maven
}
