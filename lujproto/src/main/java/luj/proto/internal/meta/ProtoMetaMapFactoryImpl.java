package luj.proto.internal.meta;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.ava.reflect.generic.GenericType;
import luj.ava.spring.Internal;
import luj.proto.internal.meta.spring.ProtoMetaHolder;

@Internal
final class ProtoMetaMapFactoryImpl implements ProtoMetaMapFactory {

  @Override
  public ProtoMetaMap create(BeanMap beanMap) {
    Map<Class<?>, ProtoMeta> metaMap = beanMap.getMetaBeans().stream()
        .map(b -> new ProtoMetaImpl(getProtoType(b), b))
        .collect(Collectors.toMap(ProtoMetaImpl::getProtoType, Function.identity()));

    return new ProtoMetaMapImpl(metaMap);
  }

  private Class<?> getProtoType(ProtoMetaHolder<?> metaBean) {
    return GenericType.fromSubclass(metaBean.getClass()).getTypeArg(0);
  }
}
