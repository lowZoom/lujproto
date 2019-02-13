package luj.proto.internal.object.decode;

import luj.ava.spring.Internal;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.object.ProtoObjectCreator;

@Internal
final class ProtoObjectDecoderFactoryImpl implements ProtoObjectDecoder.Factory {

  @Override
  public ProtoObjectDecoder create(ProtoMetaMap protoMetaMap,
      ProtoObjectCreator protoObjectCreator) {
    return new ProtoObjectDecoderImpl(protoMetaMap, protoObjectCreator);
  }
}
