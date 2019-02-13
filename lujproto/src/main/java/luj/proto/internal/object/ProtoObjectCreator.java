package luj.proto.internal.object;

import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.meta.spring.ProtoConstructor;

public interface ProtoObjectCreator {

  interface Factory {

    ProtoObjectCreator create(ProtoMetaMap protoMetaMap);
  }

  <T> T create(Class<T> protoType);

  Object create(ProtoMeta protoMeta);

  Object create(ProtoMeta protoMeta, ProtoConstructor<?> constructor, Object protoState);
}
