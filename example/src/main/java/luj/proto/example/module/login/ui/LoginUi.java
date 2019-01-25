package luj.proto.example.module.login.ui;

import luj.proto.api.LujProto;
import luj.proto.api.ProtoSession;
import luj.proto.example.core.lujproto.ProtoConf;
import luj.proto.example.module.login.protoh.LoginReqHandler;
import org.springframework.context.ApplicationContext;

public interface LoginUi {

  static LoginUi create(ApplicationContext springContext) {
    ProtoSession protoSession = LujProto.start(springContext, ProtoConf.class);
    return new LoginUiImpl(protoSession, LoginReqHandler.create(protoSession));
  }

  void show();
}
