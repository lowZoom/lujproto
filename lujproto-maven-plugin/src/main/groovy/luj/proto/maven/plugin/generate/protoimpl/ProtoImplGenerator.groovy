package luj.proto.maven.plugin.generate.protoimpl

import com.github.javaparser.ast.body.TypeDeclaration
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector

import java.nio.file.Path

interface ProtoImplGenerator {

  abstract class Factory {

    @Deprecated
    static ProtoImplGenerator create(Path dotProtoPath, TypeDeclaration javaType, String protoPackage) {
      def protoType = new ProtoTypeImpl(dotProtoPath, javaType, protoPackage)
      return new ProtoImplGeneratorImpl(protoType, protoType)
    }

    static ProtoImplGenerator create(DotProtoCollector.Proto proto, Map<String, DotProtoCollector.Proto> protoMap) {
      def protoType = new ProtoTypeImpl2(proto, protoMap)
      return new ProtoImplGeneratorImpl(protoType, protoType)
    }
  }

  interface ImplementationType {

    String getPackageName()

    String getTypeName()
  }

  ImplementationType generate()
}
