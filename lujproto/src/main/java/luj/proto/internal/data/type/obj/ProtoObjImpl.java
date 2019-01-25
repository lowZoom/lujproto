package luj.proto.internal.data.type.obj;

import luj.proto.internal.meta.spring.ProtoCodec;

final class ProtoObjImpl {

  ProtoObjImpl(Object state, ProtoCodec<?> codec) {
    _state = state;
    _codec = codec;
  }

  public Object getState() {
    return _state;
  }

  public ProtoCodec<?> getCodec() {
    return _codec;
  }

  private final Object _state;

  private final ProtoCodec<?> _codec;
}
