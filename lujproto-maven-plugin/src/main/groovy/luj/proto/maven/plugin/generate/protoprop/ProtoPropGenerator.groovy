package luj.proto.maven.plugin.generate.protoprop

import com.squareup.javapoet.ClassName
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector

interface ProtoPropGenerator {

  abstract class Factory {

    static ProtoPropGenerator create(DotProtoCollector.Proto proto, ClassName stateType) {
      def protoType = new ProtoTypeImpl2(proto, stateType)
      return new ProtoPropGeneratorImpl(protoType, protoType)
    }
  }

  void generate()
}
