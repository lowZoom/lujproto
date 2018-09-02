package luj.proto.internal.object;

import luj.proto.internal.meta.ProtoMetaMap;

public interface ProtoObjectCreator {

  interface Factory {

    ProtoObjectCreator create(ProtoMetaMap protoMetaMap);
  }

  <T> T create(Class<T> protoType);
}
