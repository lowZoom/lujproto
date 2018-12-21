package luj.proto.maven.plugin.generate.protoc

import luj.proto.maven.plugin.generate.util.maven.MavenHelper

import java.nio.file.Path

interface ProtocFindOrInstaller {

  abstract class Factory {

    static ProtocFindOrInstaller create(MavenHelper maven) {
      return new ProtocFindOrInstallerImpl(new EnvDirImpl(maven))
    }
  }

  Path findOrInstall()
}
