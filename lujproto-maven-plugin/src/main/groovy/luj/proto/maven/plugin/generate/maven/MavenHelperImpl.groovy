package luj.proto.maven.plugin.generate.maven

import groovy.transform.PackageScope
import org.apache.maven.plugin.logging.Log
import org.apache.maven.project.MavenProject

import java.nio.file.Path

@PackageScope
class MavenHelperImpl implements MavenHelper {

  MavenHelperImpl(MavenProject project, MavenPath path, Log log) {
    _project = project
    _path = path

    _log = log
  }

  @Override
  void addCompileSourceRoot(Path path) {
    _project.addCompileSourceRoot(path.toString())
  }

  @Override
  MavenPath getPath() {
    return _path
  }

  @Override
  Log getLog() {
    return _log
  }

  private final MavenProject _project
  private final MavenPath _path

  private final Log _log
}
