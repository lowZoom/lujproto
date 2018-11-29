package luj.proto.anno.proc.proto.meta;

import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import luj.generate.annotation.process.type.ProcType;

final class ProtoTypeImpl implements ProtoMetaGeneratorImpl.ProtoType {

  ProtoTypeImpl(ProcType procType) {
    _procType = procType;
  }

  @Override
  public String getTypeName() {
    return _procType.toElement().getSimpleName().toString();
  }

  @Override
  public void saveInSamePackage(TypeSpec typeSpec) throws IOException {
    _procType.getPackage().writeToFile(typeSpec);
  }

  private final ProcType _procType;
}
