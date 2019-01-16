package luj.proto.maven.plugin.generate

import com.github.javaparser.ast.body.TypeDeclaration
import com.google.common.io.Files
import com.squareup.javapoet.ClassName
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.compile.ProtoFileCompiler
import luj.proto.maven.plugin.generate.dotproto.generate.DotProtoFileGenerator
import luj.proto.maven.plugin.generate.protoconstruct.ProtoConstructGenerator
import luj.proto.maven.plugin.generate.protoimpl.ProtoImplGenerator
import luj.proto.maven.plugin.generate.protoprop.ProtoPropGenerator
import luj.proto.maven.plugin.generate.util.maven.MavenHelper

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

    //TODO: 上面部分应该执行完，收集一波，才能有足够信息生成下面的部分

    DotProtoFileGenerator.Factory.create(_declaration, protoPackage, dotProtoPath).generate()
    ProtoFileCompiler.Factory.create(dotProtoPath, protocPath, _maven).compile()

    def implementationType = ProtoImplGenerator.Factory
        .create(dotProtoPath, _declaration, protoPackage).generate()

    ClassName stateType = makeStateType(protoPackage, dotProtoPath)

    ProtoConstructGenerator.Factory.create(dotProtoPath,
        _declaration, protoPackage, implementationType, stateType).generate()

    ProtoPropGenerator.Factory.create(dotProtoPath, _declaration, stateType).generate()
  }

  private String getProtoPackage() {
    return _declaration.findCompilationUnit()
        .flatMap { it.packageDeclaration }
        .map { it.nameAsString }
        .orElse('')
  }

  private Path getDotProtoPath(String protoPackage) {
    String packagePath = protoPackage.split(/\./).join(File.separator)
    Path dirPath = _maven.path.targetGeneratedsourcesLujproto.resolve(packagePath)
    return dirPath.resolve("${_declaration.name}.proto")
  }

  private ClassName makeStateType(String packageName, Path dotProtoPath) {
    String protoName = Files.getNameWithoutExtension(dotProtoPath.toString())
    return ClassName.get(packageName, "${protoName}OuterClass", protoName, 'Builder')
  }

  private final TypeDeclaration _declaration

  private final MavenHelper _maven
}
