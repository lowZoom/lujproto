package luj.proto.internal.data.type.obj;

import luj.proto.internal.meta.spring.ProtoStateCodec;

final class ProtoObjImpl {

  ProtoObjImpl(Object state, ProtoStateCodec<?> codec) {
    _state = state;
    _codec = codec;
  }

  public Object getState() {
    return _state;
  }

  public ProtoStateCodec<?> getCodec() {
    return _codec;
  }

  @Override
  public String toString() {
    return _state.toString();
  }

  private final Object _state;

  private final ProtoStateCodec<?> _codec;
}
