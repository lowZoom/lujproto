package luj.proto.internal.object;

public interface ProtoObjectCreator {

  <T> T create(Class<T> protoType);
}
