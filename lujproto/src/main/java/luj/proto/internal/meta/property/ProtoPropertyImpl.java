package luj.proto.internal.meta.property;

import java.util.List;
import java.util.function.Function;
import luj.data.type.impl.Data;
import org.omg.CORBA.NO_IMPLEMENT;

final class ProtoPropertyImpl implements ProtoProperty {

  ProtoPropertyImpl(Function<Object, Data> dataGetter, Object valueSetter, Object valueGetter) {
    _dataGetter = dataGetter;

    _valueSetter = valueSetter;
    _valueGetter = valueGetter;
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

  public List<Class<?>> getTypeArgs() {
    throw new NO_IMPLEMENT("getTypeArgs尚未实现");
  }

  private final Function<Object, Data> _dataGetter;

  private final Object _valueSetter;
  private final Object _valueGetter;
}
