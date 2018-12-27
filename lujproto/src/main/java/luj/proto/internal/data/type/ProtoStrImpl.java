package luj.proto.internal.data.type;

import java.util.function.Consumer;
import java.util.function.Supplier;
import luj.data.type.JStr;
import luj.data.type.impl.Impl;

final class ProtoStrImpl {

  static ProtoStrImpl get(JStr str) {
    return (ProtoStrImpl) Impl.getter().apply(str);
  }

  ProtoStrImpl(Consumer<String> setter, Supplier<String> getter) {
    _setter = setter;
    _getter = getter;
  }

  public Consumer<String> getSetter() {
    return _setter;
  }

  public Supplier<String> getGetter() {
    return _getter;
  }

  private final Consumer<String> _setter;

  private final Supplier<String> _getter;
}
