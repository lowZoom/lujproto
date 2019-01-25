package luj.proto.internal.meta.spring;

public interface ProtoCodec<T> {

  byte[] encode(Object protoState);
}
