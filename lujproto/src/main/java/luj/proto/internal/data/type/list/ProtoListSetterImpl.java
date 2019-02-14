package luj.proto.internal.data.type.list;

import java.util.List;
import luj.ava.spring.Internal;
import luj.data.type.JList;
import luj.proto.internal.meta.property.ProtoProperty;
import luj.proto.internal.object.field.ProtoFieldOp;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoListSetterImpl implements ProtoListSetter {

  @Override
  public void set(JList list, List<?> val) {
    ProtoListImpl listImpl = getOrCreateListImpl(list);
    listImpl.getSetter().accept(listImpl.getProtoState(), val);
  }

  private ProtoListImpl getOrCreateListImpl(JList list) {
    ProtoListImpl oldImpl = _fieldOp.getTypeImpl(list);
    if (oldImpl != null) {
      return oldImpl;
    }

    ProtoListImpl newImpl = createListImpl(list);
    _fieldOp.setTypeImpl(list, newImpl);
    return newImpl;
  }

  @SuppressWarnings("unchecked")
  private ProtoListImpl createListImpl(JList list) {
    ProtoProperty property = _fieldOp.getProperty(list);

    return new ProtoListImpl(_fieldOp.getProtoState(list),
        property.getValueSetter());
  }

  @Autowired
  private ProtoFieldOp _fieldOp;
}
