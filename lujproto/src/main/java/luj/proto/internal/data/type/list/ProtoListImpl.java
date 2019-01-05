package luj.proto.internal.data.type.list;

import java.util.List;
import java.util.function.BiConsumer;

final class ProtoListImpl {

  ProtoListImpl(Object protoState, BiConsumer<Object, List<?>> setter) {
    _protoState = protoState;
    _setter = setter;
  }

  public Object getProtoState() {
    return _protoState;
  }

  public BiConsumer<Object, List<?>> getSetter() {
    return _setter;
  }

  private final Object _protoState;

  private final BiConsumer<Object, List<?>> _setter;
}
