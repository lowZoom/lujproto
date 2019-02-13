package luj.proto.maven.plugin.generate.protoconstruct

import com.github.javaparser.ast.body.TypeDeclaration
import com.squareup.javapoet.ClassName
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.protoimpl.ProtoImplGenerator

import java.nio.file.Path

interface ProtoConstructGenerator {

  abstract class Factory {

    @Deprecated
    static ProtoConstructGenerator create(Path dotProtoPath, TypeDeclaration javaType, String protoPackage,
                                          ProtoImplGenerator.ImplementationType implementationType, ClassName stateType) {
      def protoType = new ProtoTypeImpl(dotProtoPath, javaType, protoPackage, implementationType, stateType)
      return new ProtoConstructGeneratorImpl(protoType, protoType)
    }

    static ProtoConstructGenerator create(DotProtoCollector.Proto proto, ProtoImplGenerator.ImplementationType implementationType, ClassName stateType) {
      def protoType = new ProtoTypeImpl2(proto, implementationType, stateType)
      return new ProtoConstructGeneratorImpl(protoType, protoType)
    }
  }

  void generate()
}
