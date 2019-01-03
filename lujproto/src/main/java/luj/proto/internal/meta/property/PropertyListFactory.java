package luj.proto.internal.meta.property;

import java.util.List;
import luj.proto.internal.meta.spring.ProtoPropertyList;

public interface PropertyListFactory {

  List<ProtoProperty> create(ProtoPropertyList<?> holder);
}
