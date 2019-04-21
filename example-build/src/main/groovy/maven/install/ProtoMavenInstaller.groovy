package maven.install

import luj.ava.file.path.PathX

import java.nio.file.Path
import java.nio.file.Paths

class ProtoMavenInstaller {

  ProtoMavenInstaller(String startPath) {
    _startPath = startPath
  }

  void install() {
    Path envRoot = PathX.of(Paths.get(_startPath))
        .findParentSibling { it.file('pom.xml') }
        .findParentSibling { it.dir('envir') }
        .asPath()

    println(envRoot)
  }

  private final String _startPath
}
