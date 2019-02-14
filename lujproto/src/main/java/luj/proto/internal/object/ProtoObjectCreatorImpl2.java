package luj.proto.internal.object;

import static com.google.common.base.Preconditions.checkNotNull;

import luj.data.type.impl.Data;
import luj.proto.internal.data.type.obj.ProtoObjOp;
import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.meta.property.ProtoProperty;
import luj.proto.internal.meta.spring.ProtoConstructor;
import luj.proto.internal.object.field.ProtoFieldOp;

final class ProtoObjectCreatorImpl2 implements ProtoObjectCreator {

  ProtoObjectCreatorImpl2(ProtoMetaMap protoMetaMap,
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
    return (T) create(protoMeta);
  }

  @Override
  public Data create(ProtoMeta protoMeta) {
    ProtoConstructor<?> constructor = protoMeta.getConstructor();
    Object protoState = constructor.constructState();
    return create(protoMeta, constructor, protoState);
  }

  @Override
  public Data create(ProtoMeta protoMeta, ProtoConstructor<?> constructor, Object protoState) {
    Data protoObj = (Data) constructor.construct();
    _protoObjOp.initObjImpl(protoObj, protoState, protoMeta);

    for (ProtoProperty property : protoMeta.getPropertyList()) {
      Data fieldData = property.getFieldData(protoObj);
      _protoFieldOp.initFieldImpl(fieldData, protoState, property);
    }

    return protoObj;
  }

  private final ProtoMetaMap _protoMetaMap;

  private final ProtoObjOp _protoObjOp;
  private final ProtoFieldOp _protoFieldOp;
}
