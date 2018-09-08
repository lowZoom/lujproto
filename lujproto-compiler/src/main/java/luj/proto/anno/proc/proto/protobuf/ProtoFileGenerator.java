package luj.proto.anno.proc.proto.protobuf;

public interface ProtoFileGenerator {

  interface ProtoType {

    String getTypeName();

    void saveToFile(String path);
  }

  void generate();
}
