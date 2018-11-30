package luj.proto.anno.proc.proto.meta;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import luj.generate.annotation.process.type.ProcType;
import luj.proto.internal.meta.spring.ProtoMetaHolder;

final class ProtoTypeImpl implements ProtoMetaGeneratorImpl.ProtoType {

  ProtoTypeImpl(ProcType procType) {
    _procType = procType;
  }

  @Override
  public String getTypeName() {
    return _procType.toElement().getSimpleName().toString();
  }

  @Override
  public TypeSpec.Builder classBuilder(String name) {
    return TypeSpec.classBuilder(name)
        .superclass(getHolderClass());
  }

  @Override
  public void saveInSamePackage(TypeSpec typeSpec) throws IOException {
    _procType.getPackage().writeToFile(typeSpec);
  }

  private TypeName getHolderClass() {
    return ParameterizedTypeName.get(ClassName.get(ProtoMetaHolder.class), _procType.toTypeName());
  }

  private final ProcType _procType;
}
