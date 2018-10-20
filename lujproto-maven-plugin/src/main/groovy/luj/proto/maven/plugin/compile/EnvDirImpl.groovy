package luj.proto.maven.plugin.compile

import groovy.transform.PackageScope
import luj.groovy.AutoCtor
import luj.proto.maven.plugin.compile.protoc.ProtocInstaller
import luj.proto.maven.plugin.compile.protoc.exe.ProtocExeFinder

import java.nio.file.Paths

@PackageScope
@AutoCtor
class EnvDirImpl implements ProtoListImpl.EnvDir {

  @Override
  ProtoListImpl.InstalledProtoc findProtoc() {
    String exePath = listEnvDir()
        .findAll { it.directory }
        .findAll { it.name.startsWith("protoc-$_version-") }
        .take(1)
        .collect { it.absolutePath }
        .collect { ProtocExeFinder.Factory.create(it) }
        .collect { it.find().get() }
        .findAll { it != null }
        .withDefault { null }[0]

    if (!exePath) {
      return null
    }
    return createProtoc(exePath)
  }

  @Override
  ProtoListImpl.InstalledProtoc installProtoc(String tempDir) {

    //TODO: 判断是否已经安装好protoc

    String downloadPath = Paths.get(_envPath, tempDir)
    def installer = ProtocInstaller.Factory.create(_version, downloadPath)

    ProtocInstaller.Result result = installer.install()
    return null
  }

  private File[] listEnvDir() {
    return new File(_envPath).listFiles() ?: []
  }

  private InstalledProtocImpl createProtoc(String exePath) {
    ProjectImpl project = _outputRoot.project
    String protoRoot = project.protoRoot

    String outputPath = _outputRoot.outputPath
    return new InstalledProtocImpl(exePath, protoRoot, outputPath)
  }

  private String _envPath

  private String _version
  private OutputRootImpl _outputRoot
}
