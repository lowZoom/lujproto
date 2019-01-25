package luj.proto.internal.object.encode;

import luj.ava.spring.Internal;
import luj.data.type.impl.Data;
import luj.proto.internal.data.type.obj.ProtoObjOp;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoObjectEncoderImpl implements ProtoObjectEncoder {

  @Override
  public byte[] encode(Data objData) {
    Object protoState = _protoObjOp.getState(objData);
    return _protoObjOp.getCodec(objData).encode(protoState);
  }

  @Autowired
  private ProtoObjOp _protoObjOp;
}
