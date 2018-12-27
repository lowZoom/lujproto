package luj.proto.maven.plugin.generate.util.java

import com.squareup.javapoet.TypeSpec

import java.nio.file.Path

interface JavaClassSaver {

  abstract class Factory {

    static JavaClassSaver create(Path javaPath, String packageName, TypeSpec javaType) {
      return new JavaClassSaverImpl(javaPath, packageName, javaType)
    }
  }

  void save()
}
