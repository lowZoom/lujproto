package luj.proto.example.module.login.protoh;

import luj.proto.api.ProtoSession;
import luj.proto.example.module.login.proto.LoginReq;

final class LoginReqHandlerImpl implements LoginReqHandler {

  @Override
  public void handle(byte[] reqData) {
    LoginReq req = _protoSession.createProto(LoginReq.class);
    _protoSession.decode(req, reqData);

    System.out.println("请求登陆：" + req.account());
  }

  LoginReqHandlerImpl(ProtoSession protoSession) {
    _protoSession = protoSession;
  }

  private final ProtoSession _protoSession;
}
