package luj.proto.maven.plugin.generate.protoprop

import com.github.javaparser.ast.body.TypeDeclaration
import com.squareup.javapoet.ClassName

import java.nio.file.Path

interface ProtoPropGenerator {

  abstract class Factory {

    static ProtoPropGenerator create(Path dotProtoPath, TypeDeclaration javaType, ClassName stateType) {
      def protoType = new ProtoTypeImpl(dotProtoPath, javaType, stateType.packageName(), stateType)
      return new ProtoPropGeneratorImpl(protoType, protoType)
    }
  }

  void generate()
}
