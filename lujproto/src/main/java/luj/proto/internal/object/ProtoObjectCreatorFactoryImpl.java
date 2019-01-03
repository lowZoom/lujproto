package luj.proto.internal.object;

import luj.ava.spring.Internal;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.object.field.ProtoFieldOp;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoObjectCreatorFactoryImpl implements ProtoObjectCreator.Factory {

  @Override
  public ProtoObjectCreator create(ProtoMetaMap protoMetaMap) {
    return new ProtoObjectCreatorImpl(protoMetaMap, _protoFieldOp);
  }

  @Autowired
  private ProtoFieldOp _protoFieldOp;
}
