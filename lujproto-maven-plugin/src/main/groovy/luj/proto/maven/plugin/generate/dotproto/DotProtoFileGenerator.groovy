package luj.proto.maven.plugin.generate.dotproto

import com.github.javaparser.ast.body.TypeDeclaration
import luj.proto.maven.plugin.generate.maven.MavenHelper

interface DotProtoFileGenerator {

  abstract class Factory {

    static DotProtoFileGenerator create(TypeDeclaration declaration, MavenHelper maven) {
      def type = new ProtoTypeImpl(declaration, maven)
      return new DotProtoFileGeneratorImpl(type)
    }
  }

  void generate()
}
