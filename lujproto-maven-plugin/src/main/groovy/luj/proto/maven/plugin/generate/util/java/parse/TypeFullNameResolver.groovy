package luj.proto.maven.plugin.generate.util.java.parse

import com.github.javaparser.ast.type.Type

interface TypeFullNameResolver {

  abstract class Factory {

    static TypeFullNameResolver create(Type type) {
      return new TypeFullNameResolverImpl(type)
    }
  }

  String resolve()
}
