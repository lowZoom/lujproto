package luj.proto.maven.plugin.generate.protoimpl

import com.github.javaparser.ast.body.TypeDeclaration

import java.nio.file.Path

interface ProtoImplGenerator {

  abstract class Factory {

    static ProtoImplGenerator create(Path dotProtoPath, TypeDeclaration javaType, String protoPackage) {
      def protoType = new ProtoTypeImpl(dotProtoPath, javaType, protoPackage)
      return new ProtoImplGeneratorImpl(protoType, protoType)
    }
  }

  void generate()
}
