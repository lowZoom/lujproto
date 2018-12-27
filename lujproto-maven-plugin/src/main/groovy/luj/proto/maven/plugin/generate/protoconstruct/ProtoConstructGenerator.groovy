package luj.proto.maven.plugin.generate.protoconstruct

import com.github.javaparser.ast.body.TypeDeclaration
import luj.proto.maven.plugin.generate.protoimpl.ProtoImplGenerator

import java.nio.file.Path

interface ProtoConstructGenerator {

  abstract class Factory {

    static ProtoConstructGenerator create(Path dotProtoPath, TypeDeclaration javaType, String protoPackage,
                                          ProtoImplGenerator.ImplementationType implementationType) {
      def protoType = new ProtoTypeImpl(dotProtoPath, javaType, protoPackage, implementationType)
      return new ProtoConstructGeneratorImpl(protoType, protoType)
    }
  }

  void generate()
}
