package luj.proto.internal.data.type;

import luj.ava.spring.Internal;
import luj.data.type.JStr;
import luj.proto.internal.data.type.str.ProtoStrSetter;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoTypeSetterImpl implements ProtoTypeSetter {

  @Override
  public void setStr(JStr str, String val) {
    _strSetter.set(str, val);
  }

  @Autowired
  private ProtoStrSetter _strSetter;
}
