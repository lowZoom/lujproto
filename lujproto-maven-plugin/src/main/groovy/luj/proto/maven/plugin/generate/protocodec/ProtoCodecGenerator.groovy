package luj.proto.maven.plugin.generate.protocodec

import com.squareup.javapoet.ClassName
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector

interface ProtoCodecGenerator {

  abstract class Factory {

    static ProtoCodecGenerator create(DotProtoCollector.Proto proto, ClassName stateType) {
      return new ProtoCodecGeneratorImpl(new ProtoTypeImpl(proto), stateType)
    }
  }

  void generate()
}
