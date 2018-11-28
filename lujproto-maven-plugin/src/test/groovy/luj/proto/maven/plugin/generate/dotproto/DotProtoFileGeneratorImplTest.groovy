package luj.proto.maven.plugin.generate.dotproto

import spock.lang.Specification

class DotProtoFileGeneratorImplTest extends Specification {

  DotProtoFileGeneratorImpl.ProtoType _protoType
  List _fieldList

  String _output

  void setup() {
    _protoType = Mock(DotProtoFileGeneratorImpl.ProtoType)
    _protoType.getFieldList() >> { _fieldList.collect { mockField(it) } }
  }

  def "Generate"() {
    given:
    _protoType.getPackage() >> 'a.b.c'
    _protoType.getTypeName() >> 'TestProto'

    _fieldList = [
        ['string', 'a'],
        ['int', 'b'],
    ]

    when:
    generate()

    then:
    1 * _protoType.writeProtoFile { _output = it }
    _output.contains('package a.b.c;')
    _output.contains('message TestProto ')
    _output.contains('int b = 2;')
  }

  void generate() {
    new DotProtoFileGeneratorImpl(_protoType).generate()
  }

  def mockField(List value) {
    def mock = Stub(DotProtoFileGeneratorImpl.ProtoField)
    mock.getType() >> value[0]
    mock.getName() >> value[1]
    return mock
  }
}
