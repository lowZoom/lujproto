package luj.proto.maven.plugin.compile.protoimpl

import groovy.transform.PackageScope

@PackageScope
final class ProtoFileImpl implements ProtobufGlueMakerImpl.ProtoFile {

  ProtoFileImpl(String protoPath) {
    _protoPath = protoPath
  }

  @Override
  String getProtoName() {

    return null
  }

  private final String _protoPath
}
