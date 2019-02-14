package luj.proto.internal.session;

import java.io.IOException;
import java.util.List;
import luj.data.type.JList;
import luj.data.type.JRef;
import luj.data.type.JStr;
import luj.data.type.impl.Data;
import luj.proto.api.ProtoSession;
import luj.proto.internal.data.type.ProtoTypeGetter;
import luj.proto.internal.data.type.ProtoTypeSetter;
import luj.proto.internal.object.ProtoObjectCreator;
import luj.proto.internal.object.decode.ProtoObjectDecoder;
import luj.proto.internal.object.encode.ProtoObjectEncoder;

final class ProtoSessionImpl implements ProtoSession {

  ProtoSessionImpl(ProtoObjectCreator protoObjectCreator, ProtoTypeSetter protoTypeSetter,
      ProtoTypeGetter protoTypeGetter, ProtoObjectEncoder protoObjectEncoder,
      ProtoObjectDecoder protoObjectDecoder) {
    _protoObjectCreator = protoObjectCreator;

    _protoTypeSetter = protoTypeSetter;
    _protoTypeGetter = protoTypeGetter;

    _protoObjectEncoder = protoObjectEncoder;
    _protoObjectDecoder = protoObjectDecoder;
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
  public String get(JStr str) {
    return _protoTypeGetter.getStr(str);
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
  public <T> T decode(byte[] data, Class<T> protoType) throws IOException {
    return _protoObjectDecoder.decode(data, protoType);
  }

  private final ProtoObjectCreator _protoObjectCreator;

  private final ProtoTypeSetter _protoTypeSetter;
  private final ProtoTypeGetter _protoTypeGetter;

  private final ProtoObjectEncoder _protoObjectEncoder;
  private final ProtoObjectDecoder _protoObjectDecoder;
}
