package luj.proto.maven.plugin.generate.maven

import org.apache.maven.plugin.logging.Log
import org.apache.maven.project.MavenProject

import java.nio.file.Path

interface MavenHelper {

  abstract class Factory {

    static MavenHelper create(MavenProject project, Log log) {
      def path = new MavenPathImpl(project)
      return new MavenHelperImpl(project, path, log)
    }
  }

  void addCompileSourceRoot(Path path)

  MavenPath getPath()

  Log getLog()
}
