package luj.proto.anno.proc.proto.meta;

import com.squareup.javapoet.TypeSpec;
import java.io.IOException;

final class ProtoMetaGeneratorImpl implements ProtoMetaGenerator {

  ProtoMetaGeneratorImpl(ProtoType protoType) {
    _protoType = protoType;
  }

  @Override
  public void generate() throws IOException {
    TypeSpec metaType = TypeSpec.classBuilder(getMetaName())
        .build();

    _protoType.saveInSamePackage(metaType);
  }

  private String getMetaName() {
    return _protoType.getTypeName() + "Meta";
  }

  interface ProtoType {

    String getTypeName();

    void saveInSamePackage(TypeSpec typeSpec) throws IOException;
  }

  private final ProtoType _protoType;
}
