package luj.proto.maven.plugin.generate.protoconstruct

import com.squareup.javapoet.ClassName
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.protoimpl.ProtoImplGenerator

interface ProtoConstructGenerator {

  abstract class Factory {

    static ProtoConstructGenerator create(DotProtoCollector.Proto proto, ProtoImplGenerator.ImplementationType implementationType, ClassName stateType) {
      def protoType = new ProtoTypeImpl2(proto, implementationType, stateType)
      return new ProtoConstructGeneratorImpl(protoType, protoType)
    }
  }

  void generate()
}
