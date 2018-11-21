package luj.proto.maven.plugin.compile.protoimpl

import com.squareup.javapoet.TypeSpec

class ResultImpl implements ProtobufGlueMaker.Result {

  ResultImpl(TypeSpec builderImpl) {
    _builderImpl = builderImpl
  }

  @Override
  TypeSpec getBuilderImpl() {
    return _builderImpl
  }

  private final TypeSpec _builderImpl
}
