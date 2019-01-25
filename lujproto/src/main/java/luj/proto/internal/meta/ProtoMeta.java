package luj.proto.internal.meta;

import java.util.List;
import luj.proto.internal.meta.property.ProtoProperty;
import luj.proto.internal.meta.spring.ProtoCodec;
import luj.proto.internal.meta.spring.ProtoConstructor;

public interface ProtoMeta {

  ProtoConstructor<?> getConstructor();

  List<ProtoProperty> getPropertyList();

  ProtoCodec<?> getProtoCodec();
}
