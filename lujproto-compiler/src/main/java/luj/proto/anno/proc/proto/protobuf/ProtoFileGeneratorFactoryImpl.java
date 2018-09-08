package luj.proto.anno.proc.proto.protobuf;

import javax.lang.model.element.TypeElement;
import luj.ava.annotation.AnnoProc;
import luj.ava.annotation.SingleAnnoProc;

enum ProtoFileGeneratorFactoryImpl implements ProtoFileGeneratorFactory {
  SINGLETON;

  @Override
  public ProtoFileGenerator instance(SingleAnnoProc.Context ctx) {
    TypeElement elem = (TypeElement) ctx.getElement();
    AnnoProc.Log log = ctx.getLogger();

    ProtoFileGenerator.ProtoType protoType = new ProtoTypeImpl(elem, log);
    return new ProtoFileGeneratorImpl(protoType);
  }
}
