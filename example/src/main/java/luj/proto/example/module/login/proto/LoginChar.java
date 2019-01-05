package luj.proto.example.module.login.proto;

import luj.data.type.JStr;
import luj.proto.anno.Proto;

@Proto
public interface LoginChar {

  JStr id();

  JStr name();
}
