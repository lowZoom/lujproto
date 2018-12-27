package luj.proto.maven.plugin.generate.protoconstruct

import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import luj.proto.internal.meta.spring.ProtoConstructor
import spock.lang.Specification

class ProtoConstructGeneratorImplTest extends Specification {

  ProtoConstructGeneratorImpl.ClassSaver _saver
  String _protoName

  TypeSpec _output

  void setup() {
    _saver = Mock()
  }

  def "Generate:"() {
    given:
    _protoName = 'TestProto'

    when:
    generate()

    then:
    1 * _saver.saveInProtoPackage { _output = it }
    _output.name == 'TestProtoConstruct'
    _output.methodSpecs.size() == ProtoConstructor.methods.size()
  }

  void generate() {
    new ProtoConstructGeneratorImpl(mockProtoType(), _saver).generate()
  }

  def mockProtoType() {
    def mock = Stub(ProtoConstructGeneratorImpl.ProtoType)
    mock.getProtoName() >> { _protoName }
    mock.getImplementationType() >> { TypeName.VOID }
    return mock
  }
}
