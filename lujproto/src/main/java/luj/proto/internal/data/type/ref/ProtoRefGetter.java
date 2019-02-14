package luj.proto.internal.data.type.ref;

import luj.data.type.JRef;
import luj.proto.internal.meta.ProtoMetaMap;

public interface ProtoRefGetter {

  interface Factory {

    ProtoRefGetter create(ProtoMetaMap protoMetaMap);
  }

  <T> T get(JRef<T> ref, ProtoMetaMap metaMap);
}
