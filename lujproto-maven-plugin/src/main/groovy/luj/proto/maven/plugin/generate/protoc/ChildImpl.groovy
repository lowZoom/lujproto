package luj.proto.maven.plugin.generate.protoc

import groovy.transform.PackageScope

import java.nio.file.Files
import java.nio.file.Path

@PackageScope
class ChildImpl implements ProtocFindOrInstallerImpl.Child {

  ChildImpl(Path path) {
    _path = path
  }

  @Override
  boolean isDirectory() {
    return Files.isDirectory(_path)
  }

  @Override
  String getName() {
    return _path.getFileName().toString()
  }

  @Override
  Path findProtocExe() {
    def stream = Files.walk(_path)
    Path result = stream
        .filter { Files.isRegularFile(it) }
        .filter { it.fileName.toString() ==~ /protoc.*\.exe/ }
        .findAny()
        .orElseThrow { assert false }

    stream.close()
    return result
  }

  private final Path _path
}
