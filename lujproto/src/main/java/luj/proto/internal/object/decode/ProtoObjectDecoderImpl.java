package luj.proto.internal.object.decode;

import java.io.IOException;
import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.meta.spring.ProtoConstructor;
import luj.proto.internal.meta.spring.ProtoStateCodec;
import luj.proto.internal.object.ProtoObjectCreator;

final class ProtoObjectDecoderImpl implements ProtoObjectDecoder {

  ProtoObjectDecoderImpl(ProtoMetaMap protoMetaMap, ProtoObjectCreator protoObjectCreator) {
    _protoMetaMap = protoMetaMap;
    _protoObjectCreator = protoObjectCreator;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T decode(byte[] data, Class<T> protoType) throws IOException {
    ProtoMeta protoMeta = _protoMetaMap.get(protoType);
    ProtoConstructor<?> constructor = protoMeta.getConstructor();

    ProtoStateCodec<?> stateCodec = protoMeta.getStateCodec();
    Object protoState = stateCodec.decode(data);

    return (T) _protoObjectCreator.create(protoMeta, constructor, protoState);
  }

  private final ProtoMetaMap _protoMetaMap;

  private final ProtoObjectCreator _protoObjectCreator;
}
