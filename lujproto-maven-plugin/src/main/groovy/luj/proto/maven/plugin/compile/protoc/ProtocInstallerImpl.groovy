package luj.proto.maven.plugin.compile.protoc

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class ProtocInstallerImpl implements ProtocInstaller {

  @Override
  Result install() {
    def localZip = _remoteZip.download()
    def installSource = localZip.unzip()

    ProtocExe protoc = installSource.findProtoc()
    protoc.install()

    installSource.delete()
    return null
  }

  interface RemoteZip {

    LocalZip download()
  }

  interface LocalZip {

    InstallSource unzip()
  }

  interface InstallSource {

    ProtocExe findProtoc()

    void delete()
  }

  interface ProtocExe {

    void install()

    String getPath()
  }

  private RemoteZip _remoteZip
}
