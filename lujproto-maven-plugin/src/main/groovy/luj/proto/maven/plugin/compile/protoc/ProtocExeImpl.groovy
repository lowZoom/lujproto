package luj.proto.maven.plugin.compile.protoc

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class ProtocExeImpl implements ProtocInstallerImpl.ProtocExe {

  @Override
  void install() {
    new AntBuilder().with {
      mkdir(dir: _installTo)
      move(file: _exePath, todir: _installTo, verbose: 'true')
    }
  }

  @Override
  String getPath() {
    return _exePath
  }

  private String _exePath

  private String _installTo
}
