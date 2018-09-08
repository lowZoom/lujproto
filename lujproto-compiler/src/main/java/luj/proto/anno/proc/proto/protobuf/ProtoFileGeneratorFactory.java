package luj.proto.anno.proc.proto.protobuf;

import luj.ava.annotation.SingleAnnoProc;

public interface ProtoFileGeneratorFactory {

  static ProtoFileGeneratorFactory get() {
    return ProtoFileGeneratorFactoryImpl.SINGLETON;
  }

  ProtoFileGenerator instance(SingleAnnoProc.Context ctx);
}
