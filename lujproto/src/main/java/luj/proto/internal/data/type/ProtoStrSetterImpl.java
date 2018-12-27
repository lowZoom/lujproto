package luj.proto.internal.data.type;

import luj.ava.spring.Internal;
import luj.data.type.JStr;

@Internal
final class ProtoStrSetterImpl implements ProtoStrSetter {

  @Override
  public void set(JStr str, String val) {
    ProtoStrImpl.get(str).getSetter().accept(val);
  }
}
