package luj.proto.maven.plugin.generate.dotproto.generate

import groovy.transform.PackageScope

@PackageScope
class DotProtoFileGeneratorImpl implements DotProtoFileGenerator {

  DotProtoFileGeneratorImpl(ProtoType protoType) {
    _protoType = protoType
  }

  @Override
  void generate() {
    List<ProtoField> fieldList = _protoType.getFieldList()

    _protoType.writeProtoFile([
        'syntax="proto3";',
        "package ${_protoType.package};\n",
        makeImports(fieldList) + '\n',
        "message ${_protoType.typeName} {",
        makeFields(fieldList),
        '}\n',
    ].join('\n'))
  }

  private String makeImports(List<ProtoField> fieldList) {
    return fieldList
        .collect { it.type.protoPath }
        .findAll()
        .collect { /import "${it}";/ }
        .unique()
        .join('\n') ?: '//--{NOTHING_TO_IMPORT}--//'
  }

  private String makeFields(List<ProtoField> fieldList) {
    return fieldList.withIndex()
        .collect { ProtoField f, int i -> " ${f.type.name} ${f.name} = ${i + 1};" }
        .join('\n')
  }

  interface ProtoType {

    String getPackage()

    String getTypeName()

    List<ProtoField> getFieldList()

    void writeProtoFile(String content)
  }

  interface ProtoField {

    FieldType getType()

    String getName()
  }

  interface FieldType {

    String getProtoPath()

    String getName()
  }

  private final ProtoType _protoType
}
