package luj.proto.internal.data.type.ref;

import luj.data.type.JRef;
import luj.data.type.impl.Data;
import luj.proto.internal.data.type.obj.ProtoObjOp;
import luj.proto.internal.meta.ProtoMeta;
import luj.proto.internal.meta.ProtoMetaMap;
import luj.proto.internal.object.ProtoObjectCreator;

final class ProtoRefGetOrNewerImpl implements ProtoRefGetOrNewer {

  ProtoRefGetOrNewerImpl(ProtoRefImplGetOrCreator refImplGetOrCreator,
      ProtoObjectCreator protoObjectCreator, ProtoObjOp protoObjOp) {
    _refImplGetOrCreator = refImplGetOrCreator;

    _protoObjectCreator = protoObjectCreator;
    _protoObjOp = protoObjOp;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getOrNew(JRef<T> ref, ProtoMetaMap metaMap) {
    ProtoRefImpl impl = _refImplGetOrCreator.getOrCreate(ref, metaMap);
    Object oldObj = impl.getRefObj();
    if (oldObj != null) {
      return (T) oldObj;
    }

    ProtoMeta protoMeta = impl.getRefMeta();
    Data newObj = _protoObjectCreator.create(protoMeta);
    impl.setRefObj(newObj);

    // 关联到顶层对象state
    Object parentState = impl.getProtoState();
    Object refState = _protoObjOp.getState(newObj);
    impl.getSetter().accept(parentState, refState);

    return (T) newObj;
  }

  private final ProtoRefImplGetOrCreator _refImplGetOrCreator;

  private final ProtoObjectCreator _protoObjectCreator;
  private final ProtoObjOp _protoObjOp;
}
