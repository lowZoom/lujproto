package luj.proto.internal.data.type.ref;

import luj.ava.spring.Internal;
import luj.data.type.JRef;
import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.meta.property.ProtoProperty;
import luj.proto.internal.object.field.ProtoFieldOp;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoRefImplGetOrCreator {

  ProtoRefImpl getOrCreate(JRef<?> ref, ProtoMetaMap metaMap) {
    ProtoRefImpl oldImpl = _fieldOp.getTypeImpl(ref);
    if (oldImpl != null) {
      return oldImpl;
    }

    ProtoRefImpl newImpl = createRefImpl(ref, metaMap);
    _fieldOp.setTypeImpl(ref, newImpl);
    return newImpl;
  }

  private ProtoRefImpl createRefImpl(JRef<?> ref, ProtoMetaMap metaMap) {
    ProtoProperty property = _fieldOp.getProperty(ref);
    Class<?> refType = (Class<?>) property.getTypeArgs().get(0);
    ProtoMeta refMeta = metaMap.get(refType);

    return new ProtoRefImpl(refMeta, _fieldOp.getProtoState(ref),
        property.getValueSetter(), property.getValueGetter());
  }

  @Autowired
  private ProtoFieldOp _fieldOp;
}
