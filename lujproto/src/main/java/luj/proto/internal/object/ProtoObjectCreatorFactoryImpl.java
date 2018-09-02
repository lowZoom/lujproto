package luj.proto.internal.object;

import luj.ava.spring.Internal;
import luj.proto.internal.meta.ProtoMetaMap;

@Internal
final class ProtoObjectCreatorFactoryImpl implements ProtoObjectCreator.Factory {

  @Override
  public ProtoObjectCreator create(ProtoMetaMap protoMetaMap) {
    return new ProtoObjectCreatorImpl(protoMetaMap);
  }
}
