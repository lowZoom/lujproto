package luj.proto.maven.plugin.generate

import com.github.javaparser.JavaParser
import com.squareup.javapoet.ClassName
import groovy.transform.PackageScope
import luj.proto.anno.Proto
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.dotproto.compile.ProtoFileCompiler
import luj.proto.maven.plugin.generate.dotproto.generate.DotProtoFileGenerator
import luj.proto.maven.plugin.generate.protoc.ProtocFindOrInstaller
import luj.proto.maven.plugin.generate.protoconstruct.ProtoConstructGenerator
import luj.proto.maven.plugin.generate.protoimpl.ProtoImplGenerator
import luj.proto.maven.plugin.generate.protoprop.ProtoPropGenerator
import luj.proto.maven.plugin.generate.util.maven.MavenHelper

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

import static com.google.common.io.Files.getNameWithoutExtension

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
    Map<String, DotProtoCollector.Proto> protoMap = DotProtoCollector.Factory
        .create(protoList.collect { it.declaration }, protocPath, _maven)
        .collect()

    protoMap.values().forEach {
      DotProtoFileGenerator.Factory.create(it, protoMap).generate()
      ProtoFileCompiler.Factory.create(it).compile()

      def implType = ProtoImplGenerator.Factory.create(it, protoMap).generate()
      ClassName stateType = makeStateType(it)

      ProtoConstructGenerator.Factory.create(it, implType, stateType).generate()
      ProtoPropGenerator.Factory.create(it, stateType).generate()
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

  private ClassName makeStateType(DotProtoCollector.Proto proto) {
    String protoName = getNameWithoutExtension(proto.dotProtoPath.toString())
    return ClassName.get(proto.packageName, "${protoName}OuterClass", protoName, 'Builder')
  }

  private final MavenHelper _maven
}
