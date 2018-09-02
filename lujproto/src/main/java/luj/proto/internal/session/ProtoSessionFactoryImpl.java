package luj.proto.internal.session;

import luj.ava.spring.Internal;
import luj.proto.api.ProtoSession;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.meta.ProtoMetaMapFactory;
import luj.proto.internal.object.ProtoObjectCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Internal
final class ProtoSessionFactoryImpl implements ProtoSessionFactory {

  @Override
  public ProtoSession create(ApplicationContext springContext) {
    ProtoMetaMap metaMap = null;// _mapFactory.create(springContext);
    ProtoObjectCreator creator = _creatorFactory.create(metaMap);

    return new ProtoSessionImpl(creator);
  }

  @Autowired
  private ProtoMetaMapFactory _mapFactory;

  @Autowired
  private ProtoObjectCreator.Factory _creatorFactory;
}
