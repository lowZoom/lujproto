package luj.proto.maven.plugin.generate

import com.github.javaparser.ast.body.TypeDeclaration
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.compile.ProtoFileCompiler
import luj.proto.maven.plugin.generate.dotproto.generate.DotProtoFileGenerator
import luj.proto.maven.plugin.generate.maven.MavenHelper
import luj.proto.maven.plugin.generate.protoimpl.ProtoImplGenerator

import java.nio.file.Path

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

  }

  @Override
  void generateAll(Path protocPath) {
    String protoPackage = getProtoPackage()
    Path dotProtoPath = getDotProtoPath(protoPackage)

    DotProtoFileGenerator.Factory.create(_declaration, protoPackage, dotProtoPath).generate()
    ProtoFileCompiler.Factory.create(dotProtoPath, protocPath, _maven).compile()

    ProtoImplGenerator.Factory.create(dotProtoPath, _declaration, protoPackage).generate()
  }

  private Path getDotProtoPath(String protoPackage) {
    String packagePath = protoPackage.split(/\./).join(File.separator)
    Path dirPath = _maven.path.targetGeneratedsourcesLujproto.resolve(packagePath)
    return dirPath.resolve("${_declaration.name}.proto")
  }

  private String getProtoPackage() {
    return _declaration.findCompilationUnit()
        .flatMap { it.packageDeclaration }
        .map { it.nameAsString }
        .orElse('')
  }

  private final TypeDeclaration _declaration

  private final MavenHelper _maven
}
