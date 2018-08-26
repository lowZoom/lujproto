package luj.proto.example.login.ui;

import java.util.Scanner;
import luj.proto.api.ProtoSession;
import luj.proto.example.login.proto.LoginReq;
import luj.proto.example.login.protoh.LoginReqHandler;

final class LoginUiImpl implements LoginUi {

  @Override
  public void show() {
    System.out.println("输入账号：");
    String account = readInput();

    LoginReq req = _protoSession.createProto(LoginReq.class);
    _protoSession.set(req.account(), account);

    byte[] data = _protoSession.encode(req);
    _loginReqHandler.handle(data);
  }

  LoginUiImpl(ProtoSession protoSession, LoginReqHandler loginReqHandler) {
    _protoSession = protoSession;
    _loginReqHandler = loginReqHandler;
  }

  private String readInput() {
    try (Scanner in = new Scanner(System.in)) {
      return in.nextLine();
    }
  }

  private final ProtoSession _protoSession;

  private final LoginReqHandler _loginReqHandler;
}
