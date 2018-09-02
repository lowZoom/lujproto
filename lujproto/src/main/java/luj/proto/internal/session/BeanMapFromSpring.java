package luj.proto.internal.session;

import java.util.Collection;
import luj.proto.internal.meta.ProtoMetaMapFactory;
import luj.proto.internal.meta.spring.ProtoMetaHolder;
import org.springframework.context.ApplicationContext;

final class BeanMapFromSpring implements ProtoMetaMapFactory.BeanMap {

  BeanMapFromSpring(ApplicationContext springContext) {
    _springContext = springContext;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<ProtoMetaHolder<?>> getMetaBeans() {
    return (Collection<ProtoMetaHolder<?>>) getRaw();
  }

  private Collection<?> getRaw() {
    return _springContext.getBeansOfType(ProtoMetaHolder.class).values();
  }

  private final ApplicationContext _springContext;
}
