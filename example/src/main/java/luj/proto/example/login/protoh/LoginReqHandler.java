package luj.proto.example.login.protoh;

import luj.proto.api.ProtoSession;

public interface LoginReqHandler {

  static LoginReqHandler create(ProtoSession protoSession) {
    return new LoginReqHandlerImpl(protoSession);
  }

  void handle(byte[] reqData);
}
