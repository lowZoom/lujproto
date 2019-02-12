package luj.proto.internal.data.type;

import luj.data.type.JRef;
import luj.proto.internal.data.type.ref.ProtoRefGetter;
import luj.proto.internal.meta.ProtoMetaMap;

final class ProtoTypeGetterImpl implements ProtoTypeGetter {

  ProtoTypeGetterImpl(ProtoMetaMap protoMetaMap, ProtoRefGetter refGetter) {
    _protoMetaMap = protoMetaMap;
    _refGetter = refGetter;
  }

  @Override
  public <T> T getRef(JRef<T> ref) {
    return _refGetter.get(ref, _protoMetaMap);
  }

  private final ProtoMetaMap _protoMetaMap;

  private final ProtoRefGetter _refGetter;
}
