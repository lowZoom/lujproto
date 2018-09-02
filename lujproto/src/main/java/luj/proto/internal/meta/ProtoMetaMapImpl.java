package luj.proto.internal.meta;

import java.util.Map;

final class ProtoMetaMapImpl implements ProtoMetaMap {

  ProtoMetaMapImpl(Map<Class<?>, ProtoMeta> metaMap) {
    _metaMap = metaMap;
  }

  @Override
  public ProtoMeta get(Class<?> protoType) {
    return _metaMap.get(protoType);
  }

  private final Map<Class<?>, ProtoMeta> _metaMap;
}
