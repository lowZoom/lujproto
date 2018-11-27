package luj.proto.maven.plugin.generate.maven

import groovy.transform.PackageScope
import org.apache.maven.project.MavenProject

import java.nio.file.Path
import java.nio.file.Paths

@PackageScope
class MavenPathImpl implements MavenPath {

  MavenPathImpl(MavenProject proj) {
    _proj = proj
  }

  @Override
  Path getSrcMainJava() {
    return Paths.get(_proj.build.sourceDirectory)
  }

  @Override
  Path getTargetGeneratedsourcesLujproto() {
    return Paths.get(_proj.build.directory, 'generated-sources', 'lujproto')
  }

  private final MavenProject _proj
}
