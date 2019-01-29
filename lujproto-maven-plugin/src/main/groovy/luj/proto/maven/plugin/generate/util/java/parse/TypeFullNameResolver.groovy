package luj.proto.maven.plugin.generate.util.java.parse

import com.github.javaparser.ast.type.Type
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector

interface TypeFullNameResolver {

  abstract class Factory {

    @Deprecated
    static TypeFullNameResolver create(Type type) {
      return new TypeFullNameResolverImpl(type)
    }

    static TypeFullNameResolver create(DotProtoCollector.FieldType type, Map<String, DotProtoCollector.Proto> protoMap) {
      return new TypeFullNameResolverImpl2(type, protoMap)
    }
  }

  String resolve()
}
