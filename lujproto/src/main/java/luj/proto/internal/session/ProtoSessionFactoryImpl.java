package luj.proto.internal.session;

import luj.ava.spring.Internal;
import luj.proto.api.ProtoSession;
import luj.proto.internal.data.type.ProtoTypeGetter;
import luj.proto.internal.data.type.ProtoTypeSetter;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.meta.ProtoMetaMapFactory;
import luj.proto.internal.object.ProtoObjectCreator;
import luj.proto.internal.object.decode.ProtoObjectDecoder;
import luj.proto.internal.object.encode.ProtoObjectEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Internal
final class ProtoSessionFactoryImpl implements ProtoSessionFactory {

  @Override
  public ProtoSession create(ApplicationContext springContext) {
    ProtoMetaMapFactory.BeanMap beanMap = new BeanMapFromSpring(springContext);
    ProtoMetaMap metaMap = _mapFactory.create(beanMap);

    ProtoObjectCreator objCreator = _creatorFactory.create(metaMap);
    ProtoTypeGetter typeGetter = _typeGetterFactory.create(metaMap);

    ProtoObjectDecoder objDecoder = _objectDecoderFactory.create(metaMap, objCreator);
    return new ProtoSessionImpl(objCreator, _typeSetter, typeGetter, _objectEncoder, objDecoder);
  }

  @Autowired
  private ProtoMetaMapFactory _mapFactory;

  @Autowired
  private ProtoObjectCreator.Factory _creatorFactory;

  @Autowired
  private ProtoTypeSetter _typeSetter;

  @Autowired
  private ProtoTypeGetter.Factory _typeGetterFactory;

  @Autowired
  private ProtoObjectEncoder _objectEncoder;

  @Autowired
  private ProtoObjectDecoder.Factory _objectDecoderFactory;
}
