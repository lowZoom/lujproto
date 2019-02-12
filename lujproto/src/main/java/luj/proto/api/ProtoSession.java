package luj.proto.api;

import java.util.List;
import luj.data.type.JList;
import luj.data.type.JRef;
import luj.data.type.JStr;

public interface ProtoSession {

  <T> T createProto(Class<T> protoType);

  void set(JStr str, String value);

  void set(JList list, List<?> value);

  <T> T get(JRef<T> ref);

  byte[] encode(Object protoObj);

  void decode(Object protoObj, byte[] data);
}
