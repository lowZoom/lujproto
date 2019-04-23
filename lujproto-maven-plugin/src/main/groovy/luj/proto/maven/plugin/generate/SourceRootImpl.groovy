package luj.proto.maven.plugin.generate

import com.github.javaparser.JavaParser
import com.squareup.javapoet.ClassName
import groovy.transform.PackageScope
import luj.proto.maven.plugin.export.proto.ProtoOutExporter
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector
import luj.proto.maven.plugin.generate.dotproto.compile.ProtoFileCompiler
import luj.proto.maven.plugin.generate.dotproto.generate.DotProtoFileGenerator
import luj.proto.maven.plugin.generate.protoc.ProtocFindOrInstaller
import luj.proto.maven.plugin.generate.protocodec.ProtoCodecGenerator
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

  SourceRootImpl(MavenHelper maven, Class annoType, Path envProtoc, File protoOut) {
    _maven = maven
    _annoType = annoType

    _envProtoc = envProtoc
    _protoOut = protoOut
  }

  @Override
  List<ProtoAllGeneratorImpl.ProtoType> searchProto() {
    return walkSourceRoot()
        .collect { JavaParser.parse(it) }
        .collect { it.types }
        .findAll { it.size() == 1 }
        .collect { it[0] }
        .findAll { it.getAnnotationByClass(_annoType).isPresent() }
        .collect { new ProtoTypeImpl(it, _maven) }
  }

  @Override
  void logEmpty() {
    _maven.log.info('未找到任何协议声明。')
  }

  @Override
  void generateAll(List<ProtoAllGeneratorImpl.ProtoType> protoList) {
    _maven.addCompileSourceRoot(_maven.path.targetGeneratedsourcesLujproto)

    Path protocPath = _envProtoc ?: ProtocFindOrInstaller.Factory.create(_maven).findOrInstall()
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

      ProtoCodecGenerator.Factory.create(it, stateType).generate()
    }

    exportProtoFile()
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

  private void exportProtoFile() {
    def outPath = _protoOut && _protoOut.isDirectory() ? _protoOut.toPath() : null
    new ProtoOutExporter(outPath, _maven.path.targetGeneratedsourcesLujproto).export()
  }

  private final MavenHelper _maven
  private final Class _annoType

  private final Path _envProtoc
  private final File _protoOut
}
