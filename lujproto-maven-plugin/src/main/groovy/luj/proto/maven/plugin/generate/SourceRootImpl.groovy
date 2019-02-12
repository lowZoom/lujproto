package luj.proto.maven.plugin.generate

import com.github.javaparser.JavaParser
import groovy.transform.PackageScope
import luj.proto.anno.Proto
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.dotproto.compile.ProtoFileCompiler
import luj.proto.maven.plugin.generate.dotproto.generate.DotProtoFileGenerator
import luj.proto.maven.plugin.generate.protoc.ProtocFindOrInstaller
import luj.proto.maven.plugin.generate.protoimpl.ProtoImplGenerator
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

  /**
   * @see luj.proto.maven.plugin.generate.ProtoTypeImpl#generateAll
   */
  @Override
  void generateAll(List<ProtoAllGeneratorImpl.ProtoType> protoList) {
    _maven.addCompileSourceRoot(_maven.path.targetGeneratedsourcesLujproto)

    Path protocPath = ProtocFindOrInstaller.Factory.create(_maven).findOrInstall()

    //TODO: 收集一波proto，才能在后面展开对象字段
    //TODO: 上面部分应该执行完，收集一波，才能有足够信息生成下面的部分

    Map<String, DotProtoCollector.Proto> protoMap = DotProtoCollector.Factory
        .create(protoList.collect { it.declaration }, protocPath, _maven)
        .collect()

    protoMap.values().forEach {
      DotProtoFileGenerator.Factory.create(it, protoMap).generate()
      ProtoFileCompiler.Factory.create(it).compile()

      ProtoImplGenerator.Factory.create(it, protoMap).generate()
    }

//    protoList.each { it.generateAll(protocPath) }
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
