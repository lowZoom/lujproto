package luj.proto.maven.plugin.generate.protoimpl

import com.squareup.javapoet.TypeSpec
import spock.lang.Specification

class ProtoImplGeneratorImplTest extends Specification {

  ProtoImplGeneratorImpl.ClassSaver _saver
  String _typeName

  TypeSpec _output

  void setup() {
    _saver = Mock()
  }

  def "Generate:"() {
    given:
    _typeName = 'TestProto'

    when:
    generate()

    then:
    1 * _saver.saveInProtoPackage { _output = it }
    _output.name == 'TestProtoImpl'
  }

  void generate() {
    new ProtoImplGeneratorImpl(mockProtoType(), _saver).generate()
  }

  def mockProtoType() {
    def mock = Stub(ProtoImplGeneratorImpl.ProtoType)
    mock.getTypeName() >> { _typeName }
    return mock
  }
}
