package luj.proto.internal.object;

import static com.google.common.base.Preconditions.checkNotNull;

import luj.data.type.impl.Data;
import luj.proto.internal.data.type.obj.ProtoObjOp;
import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.meta.property.ProtoProperty;
import luj.proto.internal.meta.spring.ProtoConstructor;
import luj.proto.internal.object.field.ProtoFieldOp;

final class ProtoObjectCreatorImpl implements ProtoObjectCreator {

  ProtoObjectCreatorImpl(ProtoMetaMap protoMetaMap,
      ProtoObjOp protoObjOp, ProtoFieldOp protoFieldOp) {
    _protoMetaMap = protoMetaMap;

    _protoObjOp = protoObjOp;
    _protoFieldOp = protoFieldOp;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(Class<T> protoType) {
    ProtoMeta protoMeta = _protoMetaMap.get(protoType);
    checkNotNull(protoMeta, protoType.getName());

    ProtoConstructor<?> constructor = protoMeta.getConstructor();
    Data protoObj = (Data) constructor.construct();

    Object protoState = constructor.constructState();
    _protoObjOp.initObjImpl(protoObj, protoState, protoMeta);

    for (ProtoProperty property : protoMeta.getPropertyList()) {
      Data fieldData = property.getFieldData(protoObj);
      _protoFieldOp.initFieldImpl(fieldData, protoState, property);
    }

    return (T) protoObj;
  }

  private final ProtoMetaMap _protoMetaMap;

  private final ProtoObjOp _protoObjOp;
  private final ProtoFieldOp _protoFieldOp;
}
