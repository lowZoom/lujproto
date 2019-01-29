package luj.proto.maven.plugin.generate.dotproto.generate

import groovy.transform.PackageScope
import luj.proto.maven.plugin.generate.dotproto.collect.DotProtoCollector

import java.nio.charset.StandardCharsets
import java.nio.file.Path

@PackageScope
class ProtoTypeImpl2 implements DotProtoFileGeneratorImpl.ProtoType {

  ProtoTypeImpl2(DotProtoCollector.Proto proto, Map<String, DotProtoCollector.Proto> protoMap) {
    _proto = proto
    _protoMap = protoMap
  }

  @Override
  String getPackage() {
    return _proto.getPackageName()
  }

  @Override
  String getTypeName() {
    return _proto.getProtoName()
  }

  @Override
  List<DotProtoFileGeneratorImpl.ProtoField> getFieldList() {
    return _proto.getFieldList().collect { new ProtoFieldImpl2(it, new TypeMapImpl(), _protoMap) }
  }

  @Override
  void writeProtoFile(String content) {
    Path protoPath = _proto.getDotProtoPath()
    new AntBuilder().mkdir(dir: protoPath.parent.toString())
    protoPath.write(content, StandardCharsets.UTF_8.name())
  }

  private final DotProtoCollector.Proto _proto

  private final Map<String, DotProtoCollector.Proto> _protoMap
}
