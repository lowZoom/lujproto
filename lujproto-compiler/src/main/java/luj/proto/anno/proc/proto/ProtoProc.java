package luj.proto.anno.proc.proto;

import com.google.auto.service.AutoService;
import java.lang.annotation.Annotation;
import javax.annotation.processing.Processor;
import luj.ava.annotation.SingleAnnoProc;
import luj.proto.anno.Proto;
import luj.proto.anno.proc.proto.protobuf.ProtoFileGenerator;
import luj.proto.anno.proc.proto.protobuf.ProtoFileGeneratorFactory;

@AutoService(Processor.class)
public final class ProtoProc extends SingleAnnoProc {

  @Override
  public Class<? extends Annotation> supportedAnnotationType() {
    return Proto.class;
  }

  @Override
  public void processElement(Context ctx) {
    ProtoFileGeneratorFactory factory = ProtoFileGeneratorFactory.get();
    ProtoFileGenerator generator = factory.instance(ctx);

    generator.generate();
  }
}
