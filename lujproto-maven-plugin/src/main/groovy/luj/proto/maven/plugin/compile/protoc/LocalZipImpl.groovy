package luj.proto.maven.plugin.compile.protoc

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

import java.nio.file.Paths

@PackageScope
@AutoCtor
class LocalZipImpl implements ProtocInstallerImpl.LocalZip {

  @Override
  ProtocInstallerImpl.InstallSource unzip() {
    new AntBuilder().with {
      String zipPath = Paths.get(_downloadPath, _zipName)
      unzip(src: zipPath, dest: _downloadPath)
    }

    return new InstallSourceImpl(_downloadPath, _zipName)
  }

  private String _downloadPath

  private String _zipName
}
