package luj.proto.internal.object.decode;

import java.io.IOException;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.object.ProtoObjectCreator;

public interface ProtoObjectDecoder {

  interface Factory {

    ProtoObjectDecoder create(ProtoMetaMap protoMetaMap, ProtoObjectCreator protoObjectCreator);
  }

  <T> T decode(byte[] data, Class<T> protoType) throws IOException;
}
