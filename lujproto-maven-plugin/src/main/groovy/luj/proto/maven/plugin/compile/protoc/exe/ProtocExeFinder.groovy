package luj.proto.maven.plugin.compile.protoc.exe

interface ProtocExeFinder {

  abstract class Factory {

    static ProtocExeFinder create(String startPath) {
      return new ProtocExeFinderImpl(startPath)
    }
  }

  Optional<String> find()
}
