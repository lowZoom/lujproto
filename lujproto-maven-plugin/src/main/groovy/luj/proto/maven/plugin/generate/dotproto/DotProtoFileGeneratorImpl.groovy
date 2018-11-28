package luj.proto.maven.plugin.generate.dotproto

import groovy.transform.PackageScope

@PackageScope
class DotProtoFileGeneratorImpl implements DotProtoFileGenerator {

  DotProtoFileGeneratorImpl(ProtoType protoType) {
    _protoType = protoType
  }

  @Override
  void generate() {
    _protoType.writeProtoFile([
        'syntax="proto3";',
        "package ${_protoType.package};",
        "message ${_protoType.typeName} {",
        makeFields(),
        '}\n',
    ].join('\n'))
  }

  private String makeFields() {
    return _protoType.fieldList.withIndex()
        .collect { ProtoField f, int i -> "${f.type} ${f.name} = ${i+1};" }
        .join('\n')
  }

  interface ProtoType {

    String getPackage()

    String getTypeName()

    List<ProtoField> getFieldList()

    void writeProtoFile(String content)
  }

  interface ProtoField {

    String getType()

    String getName()
  }

  private final ProtoType _protoType
}
