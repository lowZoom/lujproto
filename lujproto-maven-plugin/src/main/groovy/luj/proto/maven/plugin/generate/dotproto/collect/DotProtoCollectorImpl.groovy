package luj.proto.maven.plugin.generate.dotproto.collect

import com.github.javaparser.ast.body.TypeDeclaration
import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.util.maven.MavenHelper

import java.nio.file.Path

@PackageScope
class DotProtoCollectorImpl implements DotProtoCollector {

  DotProtoCollectorImpl(List<TypeDeclaration> protoList, Path protocExe, MavenHelper maven) {
    _protoList = protoList

    _protocExe = protocExe
    _maven = maven
  }

  /**
   * @see luj.proto.maven.plugin.generate.SourceRootImpl#generateAll
   */
  @Override
  Map<String, Proto> collect() {
    return _protoList.collect { createProto(it) }
        .collectEntries { [("${it.packageName}.${it.protoName}".toString()): it] }
  }

  private ProtoImpl createProto(TypeDeclaration declaration) {
    String packageName = declaration.findCompilationUnit()
        .flatMap { it.packageDeclaration }
        .map { it.nameAsString }
        .orElse('')

    return new ProtoImpl(declaration, packageName, _protocExe, _maven)
  }

  private final List<TypeDeclaration> _protoList

  private final Path _protocExe
  private final MavenHelper _maven
}
