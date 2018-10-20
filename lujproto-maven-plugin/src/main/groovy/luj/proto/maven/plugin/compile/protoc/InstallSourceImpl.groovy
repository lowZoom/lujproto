package luj.proto.maven.plugin.compile.protoc

import groovy.transform.PackageScope
import luj.groovy.AutoCtor
import luj.proto.maven.plugin.compile.protoc.exe.ProtocExeFinder

import java.nio.file.Paths

@PackageScope
@AutoCtor
class InstallSourceImpl implements ProtocInstallerImpl.InstallSource {

  @Override
  ProtocInstallerImpl.ProtocExe findProtoc() {
    return ProtocExeFinder.Factory.create(_dirPath).find()
        .map { createExe(it) }
        .orElseGet { assert false }
  }

  @Override
  void delete() {
    new AntBuilder().with {
      delete(dir: _dirPath, verbose: 'true')
    }
  }

  private ProtocExeImpl createExe(String exePath) {
    String envPath = new File(_dirPath).parent
    String installDir = _zipName.replace('.zip', '')

    String installPath = Paths.get(envPath, installDir)
    return new ProtocExeImpl(exePath, installPath)
  }

  private String _dirPath

  private String _zipName
}
