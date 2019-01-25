package luj.proto.internal.data.type.obj;

import luj.data.type.impl.Data;
import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.spring.ProtoCodec;

public interface ProtoObjOp {

  void initObjImpl(Data objData, Object protoState, ProtoMeta protoMeta);

  Object getState(Data objData);

  ProtoCodec<?> getCodec(Data objData);
}
