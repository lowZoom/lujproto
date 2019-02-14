package luj.proto.internal.object.field;

import luj.proto.internal.meta.property.ProtoProperty;

final class ProtoFieldImpl {

  ProtoFieldImpl(Object protoState, ProtoProperty property) {
    _protoState = protoState;
    _property = property;
  }

  public Object getTypeImpl() {
    return _typeImpl;
  }

  public void setTypeImpl(Object typeImpl) {
    _typeImpl = typeImpl;
  }

  public Object getProtoState() {
    return _protoState;
  }

  public ProtoProperty getProperty() {
    return _property;
  }

  private Object _typeImpl;

  private final Object _protoState;

  private final ProtoProperty _property;
}
