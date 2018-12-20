package luj.proto.anno.proc.proto.meta;

import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.lang.model.element.Modifier;
import org.springframework.stereotype.Component;

final class ProtoMetaGeneratorImpl implements ProtoMetaGenerator {

  ProtoMetaGeneratorImpl(ProtoType protoType) {
    _protoType = protoType;
  }

  @Override
  public void generate() throws IOException {
    TypeSpec metaType = _protoType.classBuilder(getMetaName())
        .addModifiers(Modifier.FINAL)
        .addAnnotation(Component.class)
        .build();

    _protoType.saveInSamePackage(metaType);
  }

  private String getMetaName() {
    return _protoType.getTypeName() + "Meta";
  }

  interface ProtoType {

    TypeSpec.Builder classBuilder(String name);

    String getTypeName();

    void saveInSamePackage(TypeSpec typeSpec) throws IOException;
  }

  private final ProtoType _protoType;
}
