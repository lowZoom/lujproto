package luj.proto.maven.plugin.generate.protoc

import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.util.maven.MavenHelper

import java.nio.file.Files
import java.util.stream.Stream

@PackageScope
class EnvDirImpl implements ProtocFindOrInstallerImpl.EnvDir {

  EnvDirImpl(MavenHelper maven) {
    _maven = maven
  }

  @Override
  Stream<ProtocFindOrInstallerImpl.Child> list() {
    return Files.list(_maven.path.buildEnv)
        .map { new ChildImpl(it) }
  }

  @Override
  String getProtobufVersion() {
    return _maven.dependencies
        .findAll { it.groupId == 'com.google.protobuf' }
        .findAll { it.artifactId == 'protobuf-java' }
        .collect { it.version }
        .first()
  }

  @Override
  ProtocFindOrInstallerImpl.Child installProtoc() {
    assert false
  }

  private final MavenHelper _maven
}
