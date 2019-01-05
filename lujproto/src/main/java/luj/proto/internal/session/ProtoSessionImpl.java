package luj.proto.internal.session;

import java.util.List;
import luj.data.type.JList;
import luj.data.type.JStr;
import luj.proto.api.ProtoSession;
import luj.proto.internal.data.type.ProtoTypeSetter;
import luj.proto.internal.object.ProtoObjectCreator;
import org.omg.CORBA.NO_IMPLEMENT;

final class ProtoSessionImpl implements ProtoSession {

  ProtoSessionImpl(ProtoObjectCreator protoObjectCreator, ProtoTypeSetter protoTypeSetter) {
    _protoObjectCreator = protoObjectCreator;
    _protoTypeSetter = protoTypeSetter;
  }

  @Override
  public <T> T createProto(Class<T> protoType) {
    return _protoObjectCreator.create(protoType);
  }

  @Override
  public void set(JStr str, String value) {
    _protoTypeSetter.setStr(str, value);
  }

  @Override
  public void set(JList list, List<?> value) {
    _protoTypeSetter.setList(list, value);
  }

  @Override
  public byte[] encode(Object protoObj) {
    throw new NO_IMPLEMENT("encode尚未实现");
  }

  @Override
  public void decode(Object protoObj, byte[] data) {
    throw new NO_IMPLEMENT("decode尚未实现");
  }

  private final ProtoObjectCreator _protoObjectCreator;

  private final ProtoTypeSetter _protoTypeSetter;
}
