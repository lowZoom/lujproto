package luj.proto.internal.data.type;

import luj.data.type.JRef;
import luj.data.type.JStr;
import luj.proto.internal.meta.ProtoMetaMap;

public interface ProtoTypeGetter {

  interface Factory {

    ProtoTypeGetter create(ProtoMetaMap protoMetaMap);
  }

  String getStr(JStr str);

  <T> T getRef(JRef<T> ref);

  <T> T getOrNewRef(JRef<T> ref);
}
