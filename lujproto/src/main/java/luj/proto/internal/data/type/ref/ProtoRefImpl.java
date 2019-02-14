package luj.proto.internal.data.type.ref;

import java.util.function.BiConsumer;
import java.util.function.Function;
import luj.data.type.impl.Data;
import luj.proto.internal.meta.ProtoMeta;


final class ProtoRefImpl {

  ProtoRefImpl(ProtoMeta refMeta, Object protoState,
      BiConsumer<Object, Object> setter, Function<Object, Object> getter) {
    _refMeta = refMeta;

    _protoState = protoState;
    _setter = setter;
    _getter = getter;
  }

  public Object getRefObj() {
    return _refObj;
  }

  public void setRefObj(Data refObj) {
    _refObj = refObj;
  }

  public ProtoMeta getRefMeta() {
    return _refMeta;
  }

  public Object getProtoState() {
    return _protoState;
  }

  public BiConsumer<Object, Object> getSetter() {
    return _setter;
  }

  public Function<Object, Object> getGetter() {
    return _getter;
  }

  private Data _refObj;
  private final ProtoMeta _refMeta;

  private final Object _protoState;
  private final BiConsumer<Object, Object> _setter;
  private final Function<Object, Object> _getter;
}
