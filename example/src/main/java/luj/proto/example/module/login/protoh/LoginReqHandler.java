package luj.proto.example.module.login.protoh;

import luj.proto.api.ProtoSession;
import luj.proto.example.module.login.proto.LoginRsp;

public interface LoginReqHandler {

  static LoginReqHandler create(ProtoSession protoSession) {
    return new LoginReqHandlerImpl(protoSession);
  }

  LoginRsp handle(byte[] reqData);
}
