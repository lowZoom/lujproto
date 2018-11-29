package luj.proto.maven.plugin.generate.protoc

import groovy.transform.PackageScope

import java.nio.file.Path
import java.util.stream.Stream

@PackageScope
class ProtocFindOrInstallerImpl implements ProtocFindOrInstaller {

  ProtocFindOrInstallerImpl(EnvDir envDir) {
    _envDir = envDir
  }

  @Override
  Path findOrInstall() {
    String protobufVer = _envDir.getProtobufVersion()
    return _envDir.list()
        .filter { it.isDirectory() }
        .filter { it.name.startsWith("protoc-$protobufVer-") }
        .findAny()
        .orElseGet { _envDir.installProtoc() }
        .findProtocExe()
  }

  interface EnvDir {

    Stream<Child> list()

    String getProtobufVersion()

    Child installProtoc()
  }

  interface Child {

    boolean isDirectory()

    String getName()

    Path findProtocExe()
  }

  private final EnvDir _envDir
}
