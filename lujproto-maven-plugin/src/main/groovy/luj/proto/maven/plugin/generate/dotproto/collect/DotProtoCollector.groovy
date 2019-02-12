package luj.proto.maven.plugin.generate.dotproto.collect

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.TypeDeclaration
import luj.proto.maven.plugin.generate.util.maven.MavenHelper

import java.nio.file.Path

interface DotProtoCollector {

  abstract class Factory {

    static DotProtoCollector create(List<TypeDeclaration> protoList, Path protocExe, MavenHelper maven) {
      return new DotProtoCollectorImpl(protoList, protocExe, maven)
    }
  }

  /**
   * @see luj.proto.maven.plugin.generate.dotproto.generate.ProtoTypeImpl2
   */
  interface Proto {

    String getPackageName()

    String getProtoName()

    Path getDotProtoPath()

    List<Field> getFieldList()

    Path getProtocPath()

    MavenHelper getMavenEnv()
  }

  interface Field {

    String getName()

    FieldType getType()
  }

  interface FieldType {

    CompilationUnit getCompilationUnit()

    String getName()

    FieldType getTypeParam(int index)
  }

  Map<String, Proto> collect()
}
