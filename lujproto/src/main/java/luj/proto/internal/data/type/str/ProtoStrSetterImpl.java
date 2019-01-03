package luj.proto.internal.data.type.str;

import luj.ava.spring.Internal;
import luj.data.type.JStr;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoStrSetterImpl implements ProtoStrSetter {

  @Override
  public void set(JStr str, String val) {
    ProtoStrImpl strImpl = _strImplGetOrCreator.getOrCreate(str);
    strImpl.getSetter().accept(strImpl.getProtoState(), val);
  }

  @Autowired
  private ProtoStrImplGetOrCreator _strImplGetOrCreator;
}
