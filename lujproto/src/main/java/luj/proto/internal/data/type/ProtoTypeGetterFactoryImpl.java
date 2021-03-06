package luj.proto.internal.data.type;

import luj.ava.spring.Internal;
import luj.proto.internal.data.type.ref.ProtoRefGetter;
import luj.proto.internal.data.type.str.ProtoStrAccessor;
import luj.proto.internal.meta.ProtoMetaMap;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoTypeGetterFactoryImpl implements ProtoTypeGetter.Factory {

  @Override
  public ProtoTypeGetter create(ProtoMetaMap protoMetaMap) {
    ProtoRefGetter refGetter = _refGetterFactory.create(protoMetaMap);
    return new ProtoTypeGetterImpl(_strAccessor, refGetter, protoMetaMap);
  }

  @Autowired
  private ProtoStrAccessor _strAccessor;

  @Autowired
  private ProtoRefGetter.Factory _refGetterFactory;
}
