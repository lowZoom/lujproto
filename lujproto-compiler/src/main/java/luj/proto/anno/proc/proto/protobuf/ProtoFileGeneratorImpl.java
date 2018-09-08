package luj.proto.anno.proc.proto.protobuf;

final class ProtoFileGeneratorImpl implements ProtoFileGenerator {

  ProtoFileGeneratorImpl(ProtoType protoType) {
    _protoType = protoType;
  }

  @Override
  public void generate() {
    String typeName = _protoType.getTypeName();

    _protoType.saveToFile(typeName + ".proto");
  }

  private final ProtoType _protoType;
}
