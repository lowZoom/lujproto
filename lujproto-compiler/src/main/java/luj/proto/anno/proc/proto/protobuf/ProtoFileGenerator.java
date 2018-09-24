package luj.proto.anno.proc.proto.protobuf;

import java.io.IOException;
import luj.generate.annotation.process.ProcType;
import luj.generate.annotation.process.SingleAnnoProc;
import luj.generate.annotation.process.file.ResourceFiler;

public interface ProtoFileGenerator {

  interface Factory {

    static ProtoFileGenerator create(SingleAnnoProc.Context ctx) {
      ProcType processingType = ctx.getProcessingType();
      ResourceFiler filer = ResourceFiler.from(ctx.getFiler());

      ProtoTypeImpl protoType = new ProtoTypeImpl(processingType,
          processingType.toElement(), ctx.getLogger(), filer);

      return new ProtoFileGeneratorImpl(protoType);
    }
  }

  void generate() throws IOException;
}
