package luj.proto.anno.proc.proto;

import com.google.auto.service.AutoService;
import java.io.IOException;
import java.lang.annotation.Annotation;
import javax.annotation.processing.Processor;
import luj.generate.annotation.process.SingleAnnoProc;
import luj.proto.anno.Proto;
import luj.proto.anno.proc.proto.meta.ProtoMetaGenerator;

@AutoService(Processor.class)
public final class ProtoProc extends SingleAnnoProc {

  @Override
  public Class<? extends Annotation> supportedAnnotationType() {
    return Proto.class;
  }

  @Override
  public void processElement(Context ctx) throws IOException {
    ProtoMetaGenerator.Factory.create(ctx).generate();
  }
}
