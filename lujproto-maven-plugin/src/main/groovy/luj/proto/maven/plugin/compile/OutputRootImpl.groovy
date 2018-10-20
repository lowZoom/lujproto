package luj.proto.maven.plugin.compile

import groovy.transform.PackageScope
import luj.groovy.AutoCtor
import org.apache.maven.project.MavenProject

import java.nio.file.Paths

@PackageScope
@AutoCtor
class OutputRootImpl implements ProtoListImpl.OutputRoot {

  @Override
  ProtoListImpl.EnvDir getEnvDir(String protobufVersion) {
    String envPath = Paths.get(_mavenProj.basedir.absolutePath, 'build', 'env')
    return new EnvDirImpl(envPath, protobufVersion, this)
  }

  @Override
  void addToProjectCompile() {
    _mavenProj.addCompileSourceRoot(_outputPath)
  }

  String getOutputPath() {
    return _outputPath
  }

  ProjectImpl getProject() {
    return _project
  }

  private String _outputPath

  private ProjectImpl _project
  private MavenProject _mavenProj
}
