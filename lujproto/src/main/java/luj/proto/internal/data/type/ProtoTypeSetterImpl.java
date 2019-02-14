package luj.proto.internal.data.type;

import java.util.List;
import luj.ava.spring.Internal;
import luj.data.type.JList;
import luj.data.type.JStr;
import luj.proto.internal.data.type.list.ProtoListSetter;
import luj.proto.internal.data.type.str.ProtoStrAccessor;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoTypeSetterImpl implements ProtoTypeSetter {

  @Override
  public void setStr(JStr str, String val) {
    _strSetter.set(str, val);
  }

  @Override
  public void setList(JList list, List<?> val) {
    _listSetter.set(list, val);
  }

  @Autowired
  private ProtoStrAccessor _strSetter;

  @Autowired
  private ProtoListSetter _listSetter;
}
