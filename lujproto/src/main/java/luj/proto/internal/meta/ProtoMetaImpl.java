package luj.proto.internal.meta;

import java.util.List;
import luj.proto.internal.meta.property.ProtoProperty;
import luj.proto.internal.meta.spring.ProtoConstructor;
import luj.proto.internal.meta.spring.ProtoMetaHolder;
import luj.proto.internal.meta.spring.ProtoStateCodec;

final class ProtoMetaImpl implements ProtoMeta {

  ProtoMetaImpl(Class<?> protoType, ProtoMetaHolder<?> metaHolder,
      List<ProtoProperty> propertyList) {
    _protoType = protoType;

    _metaHolder = metaHolder;
    _propertyList = propertyList;
  }

  @Override
  public ProtoConstructor<?> getConstructor() {
    return _metaHolder.getProtoConstructor();
  }

  @Override
  public List<ProtoProperty> getPropertyList() {
    return _propertyList;
  }

  @Override
  public ProtoStateCodec<?> getStateCodec() {
    return _metaHolder.getProtoStateCodec();
  }

  Class<?> getProtoType() {
    return _protoType;
  }

  private final Class<?> _protoType;

  private final ProtoMetaHolder<?> _metaHolder;
  private final List<ProtoProperty> _propertyList;
}
