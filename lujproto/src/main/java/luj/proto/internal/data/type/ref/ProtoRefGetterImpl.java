package luj.proto.internal.data.type.ref;

import luj.data.type.JRef;
import luj.data.type.impl.Data;
import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.object.ProtoObjectCreator;

final class ProtoRefGetterImpl implements ProtoRefGetter {

  ProtoRefGetterImpl(ProtoRefImplGetOrCreator refImplGetOrCreator,
      ProtoObjectCreator protoObjectCreator) {
    _refImplGetOrCreator = refImplGetOrCreator;
    _protoObjectCreator = protoObjectCreator;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T get(JRef<T> ref, ProtoMetaMap metaMap) {
    ProtoRefImpl impl = _refImplGetOrCreator.getOrCreate(ref, metaMap);
    Object oldObj = impl.getRefObj();
    if (oldObj != null) {
      return (T) oldObj;
    }

    Object parentState = impl.getProtoState();
    Object refState = impl.getGetter().apply(parentState);

    ProtoMeta protoMeta = impl.getRefMeta();
    Data newObj = _protoObjectCreator.create(protoMeta, protoMeta.getConstructor(), refState);
    impl.setRefObj(newObj);

    return (T) newObj;
  }

  private final ProtoRefImplGetOrCreator _refImplGetOrCreator;

  private final ProtoObjectCreator _protoObjectCreator;
}
