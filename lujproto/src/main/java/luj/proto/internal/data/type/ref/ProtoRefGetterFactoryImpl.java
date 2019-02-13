package luj.proto.internal.data.type.ref;

import luj.ava.spring.Internal;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.object.ProtoObjectCreator;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoRefGetterFactoryImpl implements ProtoRefGetter.Factory {

  @Override
  public ProtoRefGetter create(ProtoMetaMap protoMetaMap) {
    ProtoObjectCreator creator = _protoObjectCreatorFactory.create(protoMetaMap);
    return new ProtoRefGetterImpl(creator, _refImplGetOrCreator);
  }

  @Autowired
  private ProtoObjectCreator.Factory _protoObjectCreatorFactory;

  @Autowired
  private ProtoRefImplGetOrCreator _refImplGetOrCreator;
}
