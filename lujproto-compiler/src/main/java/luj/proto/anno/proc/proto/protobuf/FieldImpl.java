package luj.proto.anno.proc.proto.protobuf;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import javax.lang.model.element.ExecutableElement;

final class FieldImpl implements ProtoFileGeneratorImpl.MessageField {

  FieldImpl(ExecutableElement element, TypeMap typeMap) {
    _element = element;
    _typeMap = typeMap;
  }

  @Override
  public String getType() {
    ClassName className = (ClassName) TypeName.get(_element.getReturnType());
    return _typeMap.getProtoType(className.simpleName());
  }

  @Override
  public String getName() {
    return _element.getSimpleName().toString();
  }

  interface TypeMap {

    String getProtoType(String javaType);
  }

  private final ExecutableElement _element;

  private final TypeMap _typeMap;
}
