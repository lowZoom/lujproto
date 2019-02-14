package luj.proto.internal.data.type;

import luj.data.type.JRef;
import luj.data.type.JStr;
import luj.proto.internal.data.type.ref.ProtoRefGetter;
import luj.proto.internal.data.type.str.ProtoStrAccessor;
import luj.proto.internal.meta.ProtoMetaMap;

final class ProtoTypeGetterImpl implements ProtoTypeGetter {

  ProtoTypeGetterImpl(ProtoStrAccessor strAccessor,
      ProtoRefGetter refGetter, ProtoMetaMap protoMetaMap) {
    _strAccessor = strAccessor;

    _refGetter = refGetter;
    _protoMetaMap = protoMetaMap;
  }

  @Override
  public String getStr(JStr str) {
    return _strAccessor.get(str);
  }

  @Override
  public <T> T getRef(JRef<T> ref) {
    return _refGetter.get(ref, _protoMetaMap);
  }

  private final ProtoStrAccessor _strAccessor;

  private final ProtoRefGetter _refGetter;
  private final ProtoMetaMap _protoMetaMap;
}
