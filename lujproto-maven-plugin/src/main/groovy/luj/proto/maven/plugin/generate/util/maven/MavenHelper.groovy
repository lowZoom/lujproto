package luj.proto.maven.plugin.generate.util.maven

import org.apache.maven.model.Dependency
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

  MavenPath getPath()

  Log getLog()

  void addCompileSourceRoot(Path path)

  List<Dependency> getDependencies()
}
