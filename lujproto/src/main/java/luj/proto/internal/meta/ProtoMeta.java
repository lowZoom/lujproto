package luj.proto.internal.meta;

import java.util.List;

public interface ProtoMeta {

  ProtoConstructor getConstructor();

  List<ProtoProperty> getPropertyList();
}
