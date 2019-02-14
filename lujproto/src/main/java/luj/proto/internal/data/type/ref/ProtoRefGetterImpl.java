package luj.proto.internal.data.type.ref;

import luj.ava.spring.Internal;
import luj.data.type.JRef;
import luj.proto.internal.meta.ProtoMetaMap;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoRefGetterImpl implements ProtoRefGetter {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T get(JRef<T> ref, ProtoMetaMap metaMap) {
    ProtoRefImpl impl = _refImplGetOrCreator.getOrCreate(ref, metaMap);
    return (T) impl.getRefObj();
  }

  @Autowired
  private ProtoRefImplGetOrCreator _refImplGetOrCreator;
}
