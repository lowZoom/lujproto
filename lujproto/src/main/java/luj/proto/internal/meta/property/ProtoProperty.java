package luj.proto.internal.meta.property;

import luj.data.type.impl.Data;

public interface ProtoProperty {

  Data getFieldData(Object protoObj);

  <T> T getValueSetter();

//  TypeInitializer getInitializer();
//
//  void initField(Object protoObj);
}
