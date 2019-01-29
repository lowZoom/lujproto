package luj.proto.maven.plugin.generate.dotproto.compile

import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.util.maven.MavenHelper

import java.nio.file.Path

interface ProtoFileCompiler {

  abstract class Factory {

    @Deprecated
    static ProtoFileCompiler create(Path dotProto, Path protocExe, MavenHelper maven) {
      def protoRoot = maven.path.targetGeneratedsourcesLujproto.toString()
      return new ProtoFileCompilerImpl(dotProto, protocExe.toString(), protoRoot, protoRoot)
    }

    static ProtoFileCompiler create(DotProtoCollector.Proto proto) {
      return create(proto.getDotProtoPath(), proto.getProtocPath(), proto.getMavenEnv())
    }
  }

  void compile()
}
