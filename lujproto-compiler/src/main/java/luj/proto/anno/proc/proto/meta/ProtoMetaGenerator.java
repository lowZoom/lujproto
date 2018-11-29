package luj.proto.anno.proc.proto.meta;

import java.io.IOException;
import luj.generate.annotation.process.SingleAnnoProc;

public interface ProtoMetaGenerator {

  interface Factory {

    static ProtoMetaGenerator create(SingleAnnoProc.Context ctx) {
      return new ProtoMetaGeneratorImpl(new ProtoTypeImpl(ctx.getProcessingType()));
    }
  }

  void generate() throws IOException;
}
