package luj.proto.internal.meta;

import java.util.List;
import luj.proto.internal.meta.property.ProtoProperty;
import luj.proto.internal.meta.spring.ProtoConstructor;
import luj.proto.internal.meta.spring.ProtoStateCodec;

public interface ProtoMeta {

  ProtoConstructor<?> getConstructor();

  List<ProtoProperty> getPropertyList();

  ProtoStateCodec<?> getStateCodec();
}
