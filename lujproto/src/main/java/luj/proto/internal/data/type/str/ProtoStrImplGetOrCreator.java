package luj.proto.internal.data.type.str;

import luj.ava.spring.Internal;
import luj.data.type.JStr;
import luj.proto.internal.meta.property.ProtoProperty;
import luj.proto.internal.object.field.ProtoFieldOp;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoStrImplGetOrCreator {

  ProtoStrImpl getOrCreate(JStr str) {
    ProtoStrImpl oldImpl = _fieldOp.getTypeImpl(str);
    if (oldImpl != null) {
      return oldImpl;
    }

    ProtoStrImpl newImpl = createStrImpl(str);
    _fieldOp.setTypeImpl(newImpl);
    return newImpl;
  }

  @SuppressWarnings("unchecked")
  private ProtoStrImpl createStrImpl(JStr str) {
    ProtoProperty property = _fieldOp.getProperty(str);

    return new ProtoStrImpl(_fieldOp.getProtoState(str),
        property.getValueSetter(),
        property.getValueGetter());
  }

  @Autowired
  private ProtoFieldOp _fieldOp;
}
