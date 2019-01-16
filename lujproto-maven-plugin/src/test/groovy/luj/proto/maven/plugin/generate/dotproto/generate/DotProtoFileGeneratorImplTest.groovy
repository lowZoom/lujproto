package luj.proto.maven.plugin.generate.dotproto.generate

import spock.lang.Specification

class DotProtoFileGeneratorImplTest extends Specification {

  DotProtoFileGeneratorImpl.ProtoType _protoType
  List _fieldList

  String _output

  void setup() {
    _protoType = Mock(DotProtoFileGeneratorImpl.ProtoType)
    _protoType.getFieldList() >> { _fieldList.collect { mockField(it) } }
  }

  def "Generate:"() {
    given:
    _protoType.getPackage() >> 'a.b.c'
    _protoType.getTypeName() >> 'TestProto'

    _fieldList = [
        ['string', 'a'],
        ['int', 'b'],
        ['x.y.z', 'c', 'x/y/z.proto'],
    ]

    when:
    generate()

    then:
    1 * _protoType.writeProtoFile { _output = it }

    _output.contains('package a.b.c;')
    _output.contains('message TestProto ')
    _output.contains('int b = 2;')

    _output.contains('import "x/y/z.proto";')
    _output.contains('x.y.z c = 3;')
  }

  void generate() {
    new DotProtoFileGeneratorImpl(_protoType).generate()
  }

  def mockField(List value) {
    def type = Stub(DotProtoFileGeneratorImpl.FieldType)
    type.getName() >> { value[0] }
    type.getProtoPath() >> { value.size() >= 3 ? value[2] : null }

    def field = Stub(DotProtoFileGeneratorImpl.ProtoField)
    field.getType() >> { type }
    field.getName() >> { value[1] }

    return field
  }
}
