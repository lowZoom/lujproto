package luj.proto.internal.meta.spring;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class ProtoMetaHolder<T> {

  public ProtoConstructor<T> getProtoConstructor() {
    return _protoConstructor;
  }

  @Autowired
  private ProtoConstructor<T> _protoConstructor;
}
