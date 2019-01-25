package luj.proto.internal.object.encode;

import luj.data.type.impl.Data;

public interface ProtoObjectEncoder {


  byte[] encode(Data objData);
}
