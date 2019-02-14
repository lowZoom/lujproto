package luj.proto.internal.meta.property;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;
import luj.data.type.impl.Data;

final class ProtoPropertyImpl implements ProtoProperty {

  ProtoPropertyImpl(Function<Object, Data> dataGetter, Object valueSetter,
      Object valueGetter, List<Type> typeArgs) {
    _dataGetter = dataGetter;

    _valueSetter = valueSetter;
    _valueGetter = valueGetter;

    _typeArgs = typeArgs;
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

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getValueGetter() {
    return (T) _valueGetter;
  }

  @Override
  public List<Type> getTypeArgs() {
    return _typeArgs;
  }

  private final Function<Object, Data> _dataGetter;

  private final Object _valueSetter;
  private final Object _valueGetter;

  private final List<Type> _typeArgs;
}
