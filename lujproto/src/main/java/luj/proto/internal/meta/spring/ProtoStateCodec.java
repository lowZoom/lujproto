package luj.proto.internal.meta.spring;

public interface ProtoStateCodec<T> {

  byte[] encode(Object protoState);

  Object decode(byte[] data);
}
