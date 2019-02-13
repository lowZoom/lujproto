package luj.proto.internal.data.type.obj;

import luj.ava.spring.Internal;
import luj.data.type.impl.Data;
import luj.data.type.impl.Impl;
import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.spring.ProtoStateCodec;

@Internal
final class ProtoObjOpImpl implements ProtoObjOp {

  @Override
  public void initObjImpl(Data objData, Object protoState, ProtoMeta protoMeta) {
    Impl.set(objData, new ProtoObjImpl(protoState, protoMeta.getStateCodec()));
  }

  @Override
  public Object getState(Data objData) {
    return getObjImpl(objData).getState();
  }

  @Override
  public ProtoStateCodec<?> getCodec(Data objData) {
    return getObjImpl(objData).getCodec();
  }

  private ProtoObjImpl getObjImpl(Data objData) {
    return (ProtoObjImpl) Impl.get(objData);
  }
}
