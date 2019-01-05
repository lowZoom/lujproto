package luj.proto.example.module.login.ui;

import java.util.Scanner;
import luj.proto.api.ProtoSession;
import luj.proto.example.module.login.proto.LoginReq;
import luj.proto.example.module.login.proto.LoginRsp;
import luj.proto.example.module.login.protoh.LoginReqHandler;

final class LoginUiImpl implements LoginUi {

  @Override
  public void show() {
    System.out.println("输入账号：");
    String account = readInput();

    LoginReq req = _protoSession.createProto(LoginReq.class);
    _protoSession.set(req.account(), account);

    byte[] data = _protoSession.encode(req);
    LoginRsp rsp = _loginReqHandler.handle(data);

    System.out.println("登录成功，角色信息");
    System.out.println(rsp.account());
    System.out.println(rsp.curChar());
//    System.out.println(rsp.characterList());
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
