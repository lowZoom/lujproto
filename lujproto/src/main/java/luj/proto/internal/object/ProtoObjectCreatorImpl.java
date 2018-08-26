package luj.proto.internal.object;

import luj.proto.internal.meta.ProtoConstructor;
import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.meta.ProtoProperty;

final class ProtoObjectCreatorImpl implements ProtoObjectCreator {

  @Override
  public <T> T create(Class<T> protoType) {
    ProtoMeta protoMeta = _protoMetaMap.get(protoType);

    ProtoConstructor constructor = protoMeta.getConstructor();
    T protoObj = constructor.construct();

    for (ProtoProperty property : protoMeta.getPropertyList()) {
      property.initField(protoObj);
    }

    return protoObj;
  }

  ProtoObjectCreatorImpl(ProtoMetaMap protoMetaMap) {
    _protoMetaMap = protoMetaMap;
  }

  private final ProtoMetaMap _protoMetaMap;
}
