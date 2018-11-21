package luj.proto.maven.plugin.compile.protoimpl

import com.squareup.javapoet.TypeSpec

interface ProtobufGlueMaker {

  enum Factory {

    static ProtobufGlueMaker create(String protoPath) {
      return new ProtobufGlueMakerImpl(protoPath)
    }
  }

  interface Result {

    TypeSpec getBuilderImpl()

//    TypeSpec getBuilderImplFactory()
  }

  Result make()
}
