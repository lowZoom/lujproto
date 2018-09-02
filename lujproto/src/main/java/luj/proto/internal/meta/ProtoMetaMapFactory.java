package luj.proto.internal.meta;

import java.util.Collection;
import luj.proto.internal.meta.spring.ProtoMetaHolder;

public interface ProtoMetaMapFactory {

  interface BeanMap {

    Collection<ProtoMetaHolder<?>> getMetaBeans();
  }

  ProtoMetaMap create(BeanMap beanMap);
}
