package luj.proto.internal.object.field;

import luj.ava.spring.Internal;
import luj.data.type.impl.Data;
import luj.data.type.impl.Impl;
import luj.proto.internal.meta.property.ProtoProperty;

@Internal
final class ProtoFieldOpImpl implements ProtoFieldOp {

  @Override
  public void initFieldImpl(Data fieldData, Object protoState, ProtoProperty property) {
    Impl.set(fieldData, new ProtoFieldImpl(protoState, property));
  }

  @Override
  public void setTypeImpl(Object typeImpl) {

  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getTypeImpl(Data fieldData) {
    return (T) getFieldImpl(fieldData).getTypeImpl();
  }

  @Override
  public Object getProtoState(Data fieldData) {
    return getFieldImpl(fieldData).getProtoState();
  }

  @Override
  public ProtoProperty getProperty(Data fieldData) {
    return getFieldImpl(fieldData).getProperty();
  }

  private ProtoFieldImpl getFieldImpl(Data fieldData) {
    return (ProtoFieldImpl) Impl.get(fieldData);
  }
}
