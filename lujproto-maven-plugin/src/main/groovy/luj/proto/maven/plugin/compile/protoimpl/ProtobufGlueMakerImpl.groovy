package luj.proto.maven.plugin.compile.protoimpl

import com.google.common.io.Files
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope

@PackageScope
final class ProtobufGlueMakerImpl implements ProtobufGlueMaker {

  ProtobufGlueMakerImpl(String protoPath) {
    _protoPath = protoPath
  }

  @Override
  Result make() {
    def builderImpl = TypeSpec.classBuilder("${getProtoName()}BuilderImpl")
        .build()

    return new ResultImpl(builderImpl)
  }

  private String getProtoName() {
    return Files.getNameWithoutExtension(_protoPath)
  }

  private final String _protoPath
}
