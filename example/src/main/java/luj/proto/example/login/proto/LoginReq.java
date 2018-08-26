package luj.proto.example.login.proto;

import luj.data.type.JStr;
import luj.proto.anno.Proto;

@Proto
public interface LoginReq {

  JStr account();
}
