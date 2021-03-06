package luj.proto.example.module.login.protoh;

import java.io.IOException;
import luj.proto.api.ProtoSession;
import luj.proto.example.module.login.proto.LoginChar;
import luj.proto.example.module.login.proto.LoginReq;
import luj.proto.example.module.login.proto.LoginRsp;

final class LoginReqHandlerImpl implements LoginReqHandler {

  LoginReqHandlerImpl(ProtoSession protoSession) {
    _protoSession = protoSession;
  }

  @Override
  public LoginRsp handle(byte[] reqData) {
    LoginReq req = decode(reqData, LoginReq.class);
    String account = _protoSession.get(req.account());
    System.out.println("请求登陆：" + account);

    LoginRsp rsp = _protoSession.createProto(LoginRsp.class);
    _protoSession.set(rsp.account(), account);

    loadChar(_protoSession.get(rsp.curChar()), 1);

//    _protoSession.set(rsp.characterList(), IntStream.range(1, 3)
//        .mapToObj(this::loadChar)
//        .collect(Collectors.toList()));

    return rsp;
  }

  private <T> T decode(byte[] reqData, Class<T> protoType) {
    try {
      return _protoSession.decode(reqData, protoType);

    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private LoginChar loadChar(int id) {
    LoginChar c = _protoSession.createProto(LoginChar.class);
    return loadChar(c, id);
  }

  private LoginChar loadChar(LoginChar c, int id) {
    _protoSession.set(c.id(), Integer.toString(id));
    _protoSession.set(c.name(), "角色" + id);
    return c;
  }

  private final ProtoSession _protoSession;
}
