package luj.proto.maven.plugin.compile.protoc

interface ProtocInstaller {

  abstract class Factory {

    static ProtocInstaller create(String version, String installDir) {
      return new ProtocInstallerImpl(new RemoteZipImpl(version, installDir))
    }
  }

  interface Result {

    String getExePath()
  }

  Result install()
}
