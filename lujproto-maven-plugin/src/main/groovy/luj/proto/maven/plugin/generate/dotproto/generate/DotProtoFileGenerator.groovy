package luj.proto.maven.plugin.generate.dotproto.generate

import com.github.javaparser.ast.body.TypeDeclaration

import java.nio.file.Path

interface DotProtoFileGenerator {

  abstract class Factory {

    static DotProtoFileGenerator create(
        TypeDeclaration declaration, String aPackage, Path protoPath) {
      def type = new ProtoTypeImpl(declaration, aPackage, protoPath)
      return new DotProtoFileGeneratorImpl(type)
    }
  }

  void generate()
}
