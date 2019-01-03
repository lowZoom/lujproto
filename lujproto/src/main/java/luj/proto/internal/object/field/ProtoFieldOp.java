package luj.proto.internal.object.field;

import luj.data.type.impl.Data;
import luj.proto.internal.meta.property.ProtoProperty;

public interface ProtoFieldOp {

  void initFieldImpl(Data fieldData, Object protoState, ProtoProperty property);

  void setTypeImpl(Object typeImpl);

  <T> T getTypeImpl(Data fieldData);

  Object getProtoState(Data fieldData);

  ProtoProperty getProperty(Data fieldData);
}
