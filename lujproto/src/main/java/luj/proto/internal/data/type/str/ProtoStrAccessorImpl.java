package luj.proto.internal.data.type.str;

import luj.ava.spring.Internal;
import luj.data.type.JStr;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoStrAccessorImpl implements ProtoStrAccessor {

  @Override
  public void set(JStr str, String val) {
    ProtoStrImpl impl = _strImplGetOrCreator.getOrCreate(str);
    impl.getSetter().accept(impl.getProtoState(), val);
  }

  @Override
  public String get(JStr str) {
    ProtoStrImpl impl = _strImplGetOrCreator.getOrCreate(str);
    return impl.getGetter().apply(impl.getProtoState());
  }

  @Autowired
  private ProtoStrImplGetOrCreator _strImplGetOrCreator;
}
