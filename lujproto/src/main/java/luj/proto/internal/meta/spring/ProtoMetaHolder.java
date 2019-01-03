package luj.proto.internal.meta.spring;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class ProtoMetaHolder<T> {

  public ProtoConstructor<T> getProtoConstructor() {
    return _protoConstructor;
  }

  public ProtoPropertyList<T> getPropertyList() {
    return _propertyList;
  }

  @Autowired
  private ProtoConstructor<T> _protoConstructor;

  @Autowired
  private ProtoPropertyList<T> _propertyList;
}
