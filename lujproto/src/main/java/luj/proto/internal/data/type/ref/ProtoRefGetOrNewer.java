package luj.proto.internal.data.type.ref;

import luj.data.type.JRef;
import luj.proto.internal.meta.ProtoMetaMap;

public interface ProtoRefGetOrNewer {

  interface Factory {

    ProtoRefGetOrNewer create(ProtoMetaMap protoMetaMap);
  }

  <T> T getOrNew(JRef<T> ref, ProtoMetaMap metaMap);
}
