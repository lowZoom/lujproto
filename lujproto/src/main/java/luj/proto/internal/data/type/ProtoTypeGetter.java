package luj.proto.internal.data.type;

import luj.data.type.JRef;
import luj.proto.internal.meta.ProtoMetaMap;

public interface ProtoTypeGetter {

  interface Factory {

    ProtoTypeGetter create(ProtoMetaMap protoMetaMap);
  }

  <T> T getRef(JRef<T> ref);
}
