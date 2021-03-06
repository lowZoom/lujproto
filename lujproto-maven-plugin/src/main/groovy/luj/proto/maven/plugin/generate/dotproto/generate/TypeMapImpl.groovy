package luj.proto.maven.plugin.generate.dotproto.generate

import groovy.transform.PackageScope
import luj.data.type.JInt
import luj.data.type.JStr

@PackageScope
class TypeMapImpl implements ProtoFieldImpl.TypeMap, ProtoFieldImpl2.TypeMap, ProtoFieldImpl3.TypeMap {

  @Override
  String getProtoType(String javaType) {
    return SCALAR_MAP[javaType]
  }

  private static String key(Class keyType) {
    return keyType.name
  }

  private static final Map SCALAR_MAP = [
      (key(JStr)): 'string',
      (key(JInt)): 'int32',
  ]
}
