package luj.proto.anno.proc.proto.protobuf;

import javax.lang.model.element.TypeElement;
import luj.generate.annotation.processing.AnnoProc;
import luj.generate.annotation.processing.SingleAnnoProc;

public interface ProtoFileGenerator {

  interface Factory {

    static ProtoFileGenerator create(SingleAnnoProc.Context ctx) {
      TypeElement elem = ctx.getProcessingType().toElement();
      AnnoProc.Log log = ctx.getLogger();

      ProtoTypeImpl protoType = new ProtoTypeImpl(elem, log);
      return new ProtoFileGeneratorImpl(protoType);
    }
  }

  void generate();
}
