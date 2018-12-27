package luj.proto.maven.plugin.generate.util.java

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope

import java.nio.charset.StandardCharsets
import java.nio.file.Path

@PackageScope
class JavaClassSaverImpl implements JavaClassSaver {

  JavaClassSaverImpl(Path javaPath, String packageName, TypeSpec javaType) {
    _javaPath = javaPath

    _packageName = packageName
    _javaType = javaType
  }

  @Override
  void save() {
    Writer writer = _javaPath.newWriter(StandardCharsets.UTF_8.name())
    JavaFile.builder(_packageName, _javaType).build().writeTo(writer)
    writer.close()
  }

  private final Path _javaPath

  private final String _packageName
  private final TypeSpec _javaType
}
