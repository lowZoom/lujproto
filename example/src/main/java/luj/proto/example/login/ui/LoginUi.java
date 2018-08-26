package luj.proto.example.login.ui;

import luj.proto.api.LujProto;
import luj.proto.api.ProtoSession;
import luj.proto.example.login.protoh.LoginReqHandler;

public interface LoginUi {

  static LoginUi create() {
    ProtoSession protoSession = LujProto.start();
    return new LoginUiImpl(protoSession, LoginReqHandler.create(protoSession));
  }

  void show();
}
