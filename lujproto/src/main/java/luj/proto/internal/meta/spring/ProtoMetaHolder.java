package luj.proto.internal.meta.spring;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class ProtoMetaHolder<T> {

  public ProtoConstructor<T> getProtoConstructor() {
    return _protoConstructor;
  }

  public ProtoPropertyList<T> getProtoPropertyList() {
    return _protoPropertyList;
  }

  public ProtoStateCodec<T> getProtoStateCodec() {
    return _protoStateCodec;
  }

  @Autowired
  private ProtoConstructor<T> _protoConstructor;

  @Autowired
  private ProtoPropertyList<T> _protoPropertyList;

  @Autowired
  private ProtoStateCodec<T> _protoStateCodec;
}
