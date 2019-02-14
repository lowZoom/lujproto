package luj.proto.internal.meta.property;

import java.util.List;
import luj.data.type.impl.Data;

public interface ProtoProperty {

  Data getFieldData(Object protoObj);

  <T> T getValueSetter();

  <T> T getValueGetter();

  List<Class<?>> getTypeArgs();
}
