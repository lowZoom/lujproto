package luj.proto.anno.proc.proto.protobuf;

import javax.lang.model.element.TypeElement;
import luj.ava.annotation.AnnoProc;

final class ProtoTypeImpl implements ProtoFileGenerator.ProtoType {

  ProtoTypeImpl(TypeElement elem, AnnoProc.Log log) {
    _elem = elem;
    _log = log;
  }

  @Override
  public String getTypeName() {
    return _elem.getSimpleName().toString();
  }

  @Override
  public void saveToFile(String path) {
    _log.warn(path);
  }

  private final TypeElement _elem;

  private final AnnoProc.Log _log;
}
