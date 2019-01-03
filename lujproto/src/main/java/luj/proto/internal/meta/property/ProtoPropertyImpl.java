package luj.proto.internal.meta.property;

import java.util.function.Function;
import luj.data.type.impl.Data;

final class ProtoPropertyImpl implements ProtoProperty {

  ProtoPropertyImpl(Function<Object, Data> dataGetter, Object valueSetter) {
    _dataGetter = dataGetter;
    _valueSetter = valueSetter;
  }

  @Override
  public Data getFieldData(Object protoObj) {
    return _dataGetter.apply(protoObj);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getValueSetter() {
    return (T) _valueSetter;
  }

  private final Function<Object, Data> _dataGetter;

  private final Object _valueSetter;
}
