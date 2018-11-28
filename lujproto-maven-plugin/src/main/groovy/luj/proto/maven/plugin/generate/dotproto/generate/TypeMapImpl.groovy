package luj.proto.maven.plugin.generate.dotproto.generate

import groovy.transform.PackageScope
import luj.data.type.JStr

@PackageScope
class TypeMapImpl implements ProtoFieldImpl.TypeMap {

  @Override
  String getProtoType(String javaType) {
    return MAP[javaType]
  }

  private static String key(Class keyType) {
    return keyType.simpleName
  }

  private static final Map MAP = [
      (key(JStr)): 'string',
  ]
}
