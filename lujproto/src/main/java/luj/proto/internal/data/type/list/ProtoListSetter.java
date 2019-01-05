package luj.proto.internal.data.type.list;

import java.util.List;
import luj.data.type.JList;

public interface ProtoListSetter {

  void set(JList list, List<?> val);
}
