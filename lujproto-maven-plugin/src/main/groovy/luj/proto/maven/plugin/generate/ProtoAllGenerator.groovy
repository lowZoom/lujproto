package luj.proto.maven.plugin.generate

import luj.proto.maven.plugin.generate.util.maven.MavenHelper
import org.apache.maven.plugin.logging.Log
import org.apache.maven.project.MavenProject

interface ProtoAllGenerator {

  abstract class Factory {

    static ProtoAllGenerator create(Class annoType, MavenProject project, File protoc, Log log) {
      def maven = MavenHelper.Factory.create(project, log)
      def protocToPath = protoc?.isFile() ? protoc.toPath() : null

      def sourceRoot = new SourceRootImpl(maven, annoType, protocToPath)
      return new ProtoAllGeneratorImpl(sourceRoot)
    }
  }

  void generate()
}
