package luj.proto.internal.data.type.ref;

import luj.ava.spring.Internal;
import luj.data.type.JRef;
import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.object.ProtoObjectCreator;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoRefGetterImpl implements ProtoRefGetter {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T get(JRef<T> ref, ProtoMetaMap metaMap) {
    ProtoRefImpl impl = _refImplGetOrCreator.getOrCreate(ref, metaMap);
    Object oldObj = impl.getProtoObj();
    if (oldObj != null) {
      return (T) oldObj;
    }

    ProtoMeta protoMeta = impl.getProtoMeta();
    Object newObj = _protoObjectCreator.create(protoMeta);

    impl.setProtoObj(newObj);
    return (T) newObj;
  }

  @Autowired
  private ProtoRefImplGetOrCreator _refImplGetOrCreator;

  @Autowired
  private ProtoObjectCreator _protoObjectCreator;
}
