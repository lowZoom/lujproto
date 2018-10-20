package luj.proto.maven.plugin.compile

import org.apache.maven.project.MavenProject

interface ProtoCompiler {

  abstract class Factory {

    static ProtoCompiler create(MavenProject project) {
      def projectRoot = new ProjectRootImpl(project)
      return new ProtoCompilerImpl(projectRoot)
    }
  }

  void compile()
}
