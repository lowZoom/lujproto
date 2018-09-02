package luj.proto.internal.meta;

import java.util.List;
import luj.proto.internal.meta.spring.ProtoConstructor;
import luj.proto.internal.meta.spring.ProtoMetaHolder;
import org.omg.CORBA.NO_IMPLEMENT;

final class ProtoMetaImpl implements ProtoMeta {

  ProtoMetaImpl(Class<?> protoType, ProtoMetaHolder<?> metaHolder) {
    _protoType = protoType;
    _metaHolder = metaHolder;
  }

  @Override
  public ProtoConstructor<?> getConstructor() {
    return _metaHolder.getProtoConstructor();
  }

  @Override
  public List<ProtoProperty> getPropertyList() {
    throw new NO_IMPLEMENT("getPropertyList尚未实现");
  }

  Class<?> getProtoType() {
    return _protoType;
  }

  private final Class<?> _protoType;

  private final ProtoMetaHolder<?> _metaHolder;
}
