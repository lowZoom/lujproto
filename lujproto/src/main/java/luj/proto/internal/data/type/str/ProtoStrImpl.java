package luj.proto.internal.data.type.str;

import java.util.function.BiConsumer;
import java.util.function.Function;
import luj.data.type.JStr;
import luj.data.type.impl.Impl;

final class ProtoStrImpl {

  static ProtoStrImpl get(JStr str) {
    return (ProtoStrImpl) Impl.getter().apply(str);
  }

  ProtoStrImpl(Object protoState, BiConsumer<Object, String> setter,
      Function<Object, String> getter) {
    _protoState = protoState;

    _setter = setter;
    _getter = getter;
  }

  public Object getProtoState() {
    return _protoState;
  }

  public BiConsumer<Object, String> getSetter() {
    return _setter;
  }

  public Function<Object, String> getGetter() {
    return _getter;
  }

  private final Object _protoState;

  private final BiConsumer<Object, String> _setter;
  private final Function<Object, String> _getter;
}
