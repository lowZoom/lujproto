package luj.proto.internal.session;

import java.util.List;
import luj.data.type.JList;
import luj.data.type.JRef;
import luj.data.type.JStr;
import luj.data.type.impl.Data;
import luj.proto.api.ProtoSession;
import luj.proto.internal.data.type.ProtoTypeGetter;
import luj.proto.internal.data.type.ProtoTypeSetter;
import luj.proto.internal.object.ProtoObjectCreator;
import luj.proto.internal.object.encode.ProtoObjectEncoder;
import org.omg.CORBA.NO_IMPLEMENT;

final class ProtoSessionImpl implements ProtoSession {

  ProtoSessionImpl(ProtoObjectCreator protoObjectCreator, ProtoTypeSetter protoTypeSetter,
      ProtoTypeGetter protoTypeGetter, ProtoObjectEncoder protoObjectEncoder) {
    _protoObjectCreator = protoObjectCreator;

    _protoTypeSetter = protoTypeSetter;
    _protoTypeGetter = protoTypeGetter;

    _protoObjectEncoder = protoObjectEncoder;
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
  public <T> T get(JRef<T> ref) {
    return _protoTypeGetter.getRef(ref);
  }

  @Override
  public byte[] encode(Object protoObj) {
    return _protoObjectEncoder.encode((Data) protoObj);
  }

  @Override
  public void decode(Object protoObj, byte[] data) {
    throw new NO_IMPLEMENT("decode尚未实现");
  }

  private final ProtoObjectCreator _protoObjectCreator;

  private final ProtoTypeSetter _protoTypeSetter;
  private final ProtoTypeGetter _protoTypeGetter;

  private final ProtoObjectEncoder _protoObjectEncoder;
}
