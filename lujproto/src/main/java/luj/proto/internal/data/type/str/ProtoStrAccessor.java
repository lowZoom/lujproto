package luj.proto.internal.data.type.str;

import luj.data.type.JStr;

public interface ProtoStrAccessor {

  void set(JStr str, String val);

  String get(JStr str);
}
