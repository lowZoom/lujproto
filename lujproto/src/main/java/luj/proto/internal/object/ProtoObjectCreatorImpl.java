package luj.proto.internal.object;

import static com.google.common.base.Preconditions.checkNotNull;

import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.meta.ProtoProperty;
import luj.proto.internal.meta.spring.ProtoConstructor;

final class ProtoObjectCreatorImpl implements ProtoObjectCreator {

  ProtoObjectCreatorImpl(ProtoMetaMap protoMetaMap) {
    _protoMetaMap = protoMetaMap;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(Class<T> protoType) {
    ProtoMeta protoMeta = _protoMetaMap.get(protoType);
    checkNotNull(protoMeta, protoType.getName());

    ProtoConstructor<?> constructor = protoMeta.getConstructor();
    T protoObj = (T) constructor.construct();

    for (ProtoProperty property : protoMeta.getPropertyList()) {
      property.initField(protoObj);
    }

    return protoObj;
  }

  private final ProtoMetaMap _protoMetaMap;
}
