package luj.proto.internal.meta.property

import luj.data.type.JRef
import luj.data.type.JStr
import luj.proto.internal.meta.spring.ProtoPropertyList
import spock.lang.Specification

import java.lang.reflect.Type

class PropTypeArgsGetterTest extends Specification {

  PropTypeArgsGetter _sut

  String _propName

  void setup() {
    _sut = new PropTypeArgsGetter()
  }

  def "GetTypeArgs:有参数"() {
    given:
    _propName = 'ref'

    when:
    def result = getTypeArgs()

    then:
    result == [TestProto]
  }

  def "GetTypeArgs:没参数"() {
    given:
    _propName = 'str'

    when:
    def result = getTypeArgs()

    then:
    result.isEmpty()
  }

  List<Type> getTypeArgs() {
    return _sut.getTypeArgs(TestProp, _propName)
  }

  private interface TestProto {

    /**
     * @see TestProp#ref
     */
    JRef<TestProto> ref()

    /**
     * @see TestProp#str
     */
    JStr str()
  }

  private class TestProp implements ProtoPropertyList<TestProto> {

    /**
     * @see TestProto#ref
     */
    Object[] ref() {
      return []
    }

    /**
     * @see TestProto#str
     */
    Object[] str() {
      return []
    }
  }
}
