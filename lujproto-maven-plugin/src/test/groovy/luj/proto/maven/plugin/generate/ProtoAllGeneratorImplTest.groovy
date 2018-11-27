package luj.proto.maven.plugin.generate

import spock.lang.Specification

class ProtoAllGeneratorImplTest extends Specification {

  ProtoAllGeneratorImpl.SourceRoot _sourceRoot
  List _protoList

  List _output

  void setup() {
    _sourceRoot = Stub(ProtoAllGeneratorImpl.SourceRoot)
    _output = []
  }

  def "Generate:有非接口"() {
    given:
    _protoList = [
        ['proto1', 'interface'],
        ['proto2', 'class'],
    ]

    when:
    generate()

    then:
    _output == ['proto2.logWrongType']
  }

  void generate() {
    new ProtoAllGeneratorImpl(mockSourceRoot()).generate()
  }

  ProtoAllGeneratorImpl.SourceRoot mockSourceRoot() {
    _sourceRoot.searchProto() >> _protoList.collect { mockProtoType(it) }
    return _sourceRoot
  }

  ProtoAllGeneratorImpl.ProtoType mockProtoType(List value) {
    def mock = Stub(ProtoAllGeneratorImpl.ProtoType)
    mock.isInterface() >> { value[1] == 'interface' }
    mock.logWrongType() >> { _output << "${value[0]}.logWrongType" }
    return mock
  }
}
