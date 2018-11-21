package luj.proto.maven.plugin.compile.protoimpl

interface ProtobufGlueMaker {

  enum Factory {

    static ProtobufGlueMaker create() {
      return new ProtobufGlueMakerImpl()
    }
  }

  interface Result {

    TypeSpe
  }

  Result make()
}
