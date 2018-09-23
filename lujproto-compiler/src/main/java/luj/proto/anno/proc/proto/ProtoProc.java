package luj.proto.anno.proc.proto;

import com.google.auto.service.AutoService;
import java.lang.annotation.Annotation;
import javax.annotation.processing.Processor;
import luj.generate.annotation.processing.SingleAnnoProc;
import luj.proto.anno.Proto;
import luj.proto.anno.proc.proto.protobuf.ProtoFileGenerator;

@AutoService(Processor.class)
public final class ProtoProc extends SingleAnnoProc {

  @Override
  public Class<? extends Annotation> supportedAnnotationType() {
    return Proto.class;
  }

  @Override
  public void processElement(Context ctx) {
    ProtoFileGenerator generator = ProtoFileGenerator.Factory.create(ctx);
    generator.generate();
  }
}
