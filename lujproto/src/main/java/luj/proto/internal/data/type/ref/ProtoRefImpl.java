package luj.proto.internal.data.type.ref;

import luj.proto.internal.meta.ProtoMeta;

final class ProtoRefImpl {

  ProtoRefImpl(ProtoMeta protoMeta) {
    _protoMeta = protoMeta;
  }

  public Object getProtoObj() {
    return _protoObj;
  }

  public void setProtoObj(Object protoObj) {
    _protoObj = protoObj;
  }

  public ProtoMeta getProtoMeta() {
    return _protoMeta;
  }

  private Object _protoObj;

  private final ProtoMeta _protoMeta;
}
