package luj.proto.maven.plugin.generate.dotproto.generate

import com.github.javaparser.ast.body.TypeDeclaration
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector

import java.nio.file.Path

interface DotProtoFileGenerator {

  abstract class Factory {

    @Deprecated
    static DotProtoFileGenerator create(
        TypeDeclaration declaration, String aPackage, Path protoPath) {
      def type = new ProtoTypeImpl(declaration, aPackage, protoPath)
      return new DotProtoFileGeneratorImpl(type)
    }

    static DotProtoFileGenerator create(DotProtoCollector.Proto proto, Map<String, DotProtoCollector.Proto> protoMap) {
      return new DotProtoFileGeneratorImpl(new ProtoTypeImpl2(proto, protoMap))

    }
  }

  void generate()
}
