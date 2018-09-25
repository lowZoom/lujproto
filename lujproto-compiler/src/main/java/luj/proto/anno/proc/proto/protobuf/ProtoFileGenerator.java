package luj.proto.anno.proc.proto.protobuf;

import java.io.IOException;
import javax.lang.model.element.TypeElement;
import luj.generate.annotation.process.ProcType;
import luj.generate.annotation.process.SingleAnnoProc;

public interface ProtoFileGenerator {

  interface Factory {

    static ProtoFileGenerator create(SingleAnnoProc.Context ctx) {
      ProcType processingType = ctx.getProcessingType();
      TypeElement elem = processingType.toElement();

      MessageImpl message = new MessageImpl(processingType.getPackageName(),
          elem.getSimpleName().toString(), elem, ctx.getFiler(), ctx.getLogger());

      return new ProtoFileGeneratorImpl(message);
    }
  }

  void generate() throws IOException;
}
