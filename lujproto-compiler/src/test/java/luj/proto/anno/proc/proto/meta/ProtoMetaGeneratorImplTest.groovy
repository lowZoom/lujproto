package luj.proto.anno.proc.proto.meta

import com.squareup.javapoet.TypeSpec
import spock.lang.Specification

class ProtoMetaGeneratorImplTest extends Specification {

  ProtoMetaGeneratorImpl.ProtoType _protoType
  String _typeName

  TypeSpec _output

  void setup() {
    _protoType = Mock(ProtoMetaGeneratorImpl.ProtoType)
    _protoType.getTypeName() >> { _typeName }
  }

  def "Generate:"() {
    given:
    _typeName = 'TestProto'

    when:
    generate()

    then:
    1 * _protoType.saveInSamePackage { _output = it }
    _output.name == 'TestProtoMeta'
  }

  void generate() {
    new ProtoMetaGeneratorImpl(_protoType).generate()
  }
}
