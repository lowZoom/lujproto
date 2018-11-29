package luj.proto.maven.plugin.generate.dotproto.compile

import luj.proto.maven.plugin.generate.maven.MavenHelper

import java.nio.file.Path

interface ProtoFileCompiler {

  abstract class Factory {

    static ProtoFileCompiler create(Path dotProto, Path protocExe, MavenHelper maven) {
      def protoRoot = maven.path.targetGeneratedsourcesLujproto.toString()
      return new ProtoFileCompilerImpl(dotProto, protocExe.toString(), protoRoot, protoRoot)
    }
  }

  void compile()
}
