package luj.proto.internal.data.type;

import java.util.List;
import luj.data.type.JList;
import luj.data.type.JStr;

public interface ProtoTypeSetter {

  void setStr(JStr str, String val);

  void setList(JList list, List<?> val);
}
