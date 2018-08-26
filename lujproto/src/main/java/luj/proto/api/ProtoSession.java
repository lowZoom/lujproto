package luj.proto.api;

import luj.data.type.JStr;

public interface ProtoSession {

  <T> T createProto(Class<T> protoType);

  void set(JStr str, String value);

  byte[] encode(Object protoObj);

  void decode(Object protoObj, byte[] data);
}
