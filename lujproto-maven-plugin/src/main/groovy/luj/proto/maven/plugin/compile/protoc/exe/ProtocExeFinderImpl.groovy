package luj.proto.maven.plugin.compile.protoc.exe

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

import java.nio.file.Files
import java.nio.file.Paths

@PackageScope
@AutoCtor
class ProtocExeFinderImpl implements ProtocExeFinder {

  @Override
  Optional<String> find() {
    def stream = Files.walk(Paths.get(_startPath))
    try {
      return stream
          .filter { Files.isRegularFile(it) }
          .filter { it.fileName.toString() ==~ /protoc.*\.exe/ }
          .findAny()
          .map { it.toString() }

    } finally {
      stream.close()
    }
  }

  private String _startPath
}
