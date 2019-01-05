package luj.proto.maven.plugin.generate

import com.github.javaparser.JavaParser
import groovy.transform.PackageScope
import luj.proto.anno.Proto
import luj.proto.maven.plugin.generate.protoc.ProtocFindOrInstaller
import luj.proto.maven.plugin.generate.util.maven.MavenHelper

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

@PackageScope
class SourceRootImpl implements ProtoAllGeneratorImpl.SourceRoot {

  SourceRootImpl(MavenHelper maven) {
    _maven = maven
  }

  @Override
  List<ProtoAllGeneratorImpl.ProtoType> searchProto() {
    return walkSourceRoot()
        .collect { JavaParser.parse(it) }
        .collect { it.types }
        .findAll { it.size() == 1 }
        .collect { it[0] }
        .findAll { it.getAnnotationByClass(Proto).isPresent() }
        .collect { new ProtoTypeImpl(it, _maven) }
  }

  @Override
  void logEmpty() {
    _maven.log.info('未找到任何协议声明。')
  }

  @Override
  void generateAll(List<ProtoAllGeneratorImpl.ProtoType> protoList) {
    _maven.addCompileSourceRoot(_maven.path.targetGeneratedsourcesLujproto)

    Path protocPath = ProtocFindOrInstaller.Factory.create(_maven).findOrInstall()

    //TODO: 收集一波proto，才能在后面展开对象字段

    protoList.each { it.generateAll(protocPath) }
  }

  private List<Path> walkSourceRoot() {
    def stream = Files.walk(_maven.path.srcMainJava)
    def result = stream
        .filter { Files.isRegularFile(it) }
        .collect(Collectors.toList())

    stream.close()
    return result
  }

  private final MavenHelper _maven
}
