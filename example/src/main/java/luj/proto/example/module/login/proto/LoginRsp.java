package luj.proto.example.module.login.proto;

import luj.data.type.JStr;
import luj.proto.anno.Proto;

@Proto
public interface LoginRsp {

  JStr account();

  LoginChar curChar();

//  @Type(LoginChar.class)
//  JList characterList();
}
