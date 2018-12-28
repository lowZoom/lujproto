package luj.proto.internal.session;

import luj.ava.spring.Internal;
import luj.proto.api.ProtoSession;
import luj.proto.internal.data.type.ProtoTypeSetter;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.meta.ProtoMetaMapFactory;
import luj.proto.internal.object.ProtoObjectCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Internal
final class ProtoSessionFactoryImpl implements ProtoSessionFactory {

  @Override
  public ProtoSession create(ApplicationContext springContext) {
    ProtoMetaMapFactory.BeanMap beanMap = new BeanMapFromSpring(springContext);
    ProtoMetaMap metaMap = _mapFactory.create(beanMap);

    ProtoObjectCreator creator = _creatorFactory.create(metaMap);
    return new ProtoSessionImpl(creator, _typeSetter);
  }

  @Autowired
  private ProtoMetaMapFactory _mapFactory;

  @Autowired
  private ProtoObjectCreator.Factory _creatorFactory;

  @Autowired
  private ProtoTypeSetter _typeSetter;
}
