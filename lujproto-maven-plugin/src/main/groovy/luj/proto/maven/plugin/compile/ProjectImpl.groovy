package luj.proto.maven.plugin.compile

import groovy.transform.PackageScope
import luj.groovy.AutoCtor
import org.apache.maven.model.Dependency
import org.apache.maven.project.MavenProject

import java.nio.file.Path
import java.nio.file.Paths

@PackageScope
@AutoCtor
class ProjectImpl implements ProtoListImpl.Project {

  @Override
  ProtoListImpl.OutputRoot getOutputRoot(String outputDir) {
    String generateRoot = _protoRoot.parent
    String outputPath = Paths.get(generateRoot, outputDir)

    return new OutputRootImpl(outputPath, this, _project)
  }

  @Override
  String getProtobufVersion() {
    return (_project.dependencies as List<Dependency>)
        .findAll { it.groupId == 'com.google.protobuf' }
        .findAll { it.artifactId == 'protobuf-java' }
        .stream().findAny()
        .map { it.version }
        .orElseGet { assert false }
  }

  Path getProtoRoot() {
    return _protoRoot
  }

  private MavenProject _project

  private Path _protoRoot
}
