package luj.proto.internal.meta.spring;

import java.io.IOException;

public interface ProtoStateCodec<T> {

  byte[] encode(Object protoState);

  Object decode(byte[] data) throws IOException;
}
