package luj.proto.internal.session;

import luj.data.type.JStr;
import luj.proto.api.ProtoSession;
import luj.proto.internal.object.ProtoObjectCreator;
import org.omg.CORBA.NO_IMPLEMENT;

final class ProtoSessionImpl implements ProtoSession {

  @Override
  public <T> T createProto(Class<T> protoType) {
    return _protoObjectCreator.create(protoType);
  }

  @Override
  public void set(JStr str, String value) {
    throw new NO_IMPLEMENT("set尚未实现");
  }

  @Override
  public byte[] encode(Object protoObj) {
    throw new NO_IMPLEMENT("encode尚未实现");
  }

  @Override
  public void decode(Object protoObj, byte[] data) {
    throw new NO_IMPLEMENT("decode尚未实现");
  }

  ProtoSessionImpl(ProtoObjectCreator protoObjectCreator) {
    _protoObjectCreator = protoObjectCreator;
  }

  private final ProtoObjectCreator _protoObjectCreator;
}
