package luj.proto.internal.object;

import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import org.omg.CORBA.NO_IMPLEMENT;

public interface ProtoObjectCreator {

  interface Factory {

    ProtoObjectCreator create(ProtoMetaMap protoMetaMap);
  }

  <T> T create(Class<T> protoType);

  default Object create(ProtoMeta protoMeta) {
    throw new NO_IMPLEMENT("create尚未实现");
  }
}
