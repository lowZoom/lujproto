package luj.proto.anno.proc.proto.protobuf;

import java.io.IOException;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import luj.generate.annotation.process.AnnoProc;
import luj.generate.annotation.process.AnnoProc.Log;
import luj.generate.annotation.process.ProcType;
import luj.generate.annotation.process.file.ResourceFiler;
import luj.generate.annotation.process.file.ResourceFiler.File;

final class ProtoTypeImpl implements ProtoFileGeneratorImpl.ProtoType {

  ProtoTypeImpl(ProcType procType, TypeElement elem, Log log, ResourceFiler resourceFiler) {
    _procType = procType;
    _elem = elem;

    _log = log;
    _resourceFiler = resourceFiler;
  }

  @Override
  public String getTypeName() {
    return _elem.getSimpleName().toString();
  }

  @Override
  public void saveToFile(String path) throws IOException {
    _log.warn(path);

    String packageName = _procType.getPackageName();
    String fileName = getTypeName() + ".proto";

    File protoFile = _resourceFiler.createFile("syntax = \"proto3\";");
    protoFile.writeTo(StandardLocation.SOURCE_OUTPUT, packageName, fileName, _elem);
  }

  private final ProcType _procType;
  private final TypeElement _elem;

  private final AnnoProc.Log _log;
  private final ResourceFiler _resourceFiler;
}
