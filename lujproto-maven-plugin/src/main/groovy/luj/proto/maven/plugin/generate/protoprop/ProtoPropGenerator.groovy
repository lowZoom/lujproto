package luj.proto.maven.plugin.generate.protoprop

import com.github.javaparser.ast.body.TypeDeclaration

import java.nio.file.Path

interface ProtoPropGenerator {

  abstract class Factory {

    static ProtoPropGenerator create(Path dotProtoPath, TypeDeclaration javaType, String protoPackage) {
      def protoType = new ProtoTypeImpl(dotProtoPath, javaType, protoPackage)
      return new ProtoPropGeneratorImpl(protoType, protoType)
    }
  }

  void generate()
}
