package luj.proto.example.module.login.proto;

import luj.data.type.JStr;
import luj.proto.anno.Proto;

@Proto
public interface LoginReq {

  //TODO: 支持换成String，因为请求是只读
  JStr account();
}
