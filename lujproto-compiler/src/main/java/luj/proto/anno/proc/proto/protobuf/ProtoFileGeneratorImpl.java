package luj.proto.anno.proc.proto.protobuf;

import java.io.IOException;

final class ProtoFileGeneratorImpl implements ProtoFileGenerator {

  interface ProtoType {

    String getTypeName();

    void saveToFile(String path) throws IOException;
  }

  ProtoFileGeneratorImpl(ProtoType protoType) {
    _protoType = protoType;
  }

  @Override
  public void generate() throws IOException {
    String typeName = _protoType.getTypeName();

    _protoType.saveToFile(typeName + ".proto");
  }

  private final ProtoType _protoType;
}
