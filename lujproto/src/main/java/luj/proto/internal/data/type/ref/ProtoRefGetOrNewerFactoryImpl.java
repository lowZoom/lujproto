package luj.proto.internal.data.type.ref;

import luj.ava.spring.Internal;
import luj.proto.internal.data.type.obj.ProtoObjOp;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.object.ProtoObjectCreator;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ProtoRefGetOrNewerFactoryImpl implements ProtoRefGetOrNewer.Factory {

  @Override
  public ProtoRefGetOrNewer create(ProtoMetaMap protoMetaMap) {
    ProtoObjectCreator creator = _protoObjectCreatorFactory.create(protoMetaMap);
    return new ProtoRefGetOrNewerImpl(_refImplGetOrCreator, creator, _protoObjOp);
  }

  @Autowired
  private ProtoRefImplGetOrCreator _refImplGetOrCreator;

  @Autowired
  private ProtoObjectCreator.Factory _protoObjectCreatorFactory;

  @Autowired
  private ProtoObjOp _protoObjOp;
}
