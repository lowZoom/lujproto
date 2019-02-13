package luj.proto.maven.plugin.generate.dotproto.collect

import com.github.javaparser.ast.body.TypeDeclaration
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.util.maven.MavenHelper

import java.nio.file.Path

@PackageScope
class ProtoImpl implements DotProtoCollector.Proto {

  ProtoImpl(TypeDeclaration declaration, String packageName, Path protocExe, MavenHelper maven) {
    _declaration = declaration
    _packageName = packageName

    _protocExe = protocExe
    _maven = maven
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
  Path getDotProtoPath() {
    String packagePath = _packageName.split(/\./).join(File.separator)
    Path dirPath = _maven.path.targetGeneratedsourcesLujproto.resolve(packagePath)
    return dirPath.resolve("${_declaration.name}.proto")
  }

  @Override
  List<DotProtoCollector.Field> getFieldList() {
    return _declaration.methods.collect { new FieldImpl(it) }
  }

  @Override
  Path getProtocPath() {
    return _protocExe
  }

  @Override
  MavenHelper getMavenEnv() {
    return _maven
  }

  private final TypeDeclaration _declaration
  private final String _packageName

  private final Path _protocExe
  private final MavenHelper _maven
}
