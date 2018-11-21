package luj.proto.maven.plugin.compile.protoimpl

import spock.lang.Specification

import java.nio.file.Path
import java.nio.file.Paths

class ProtobufGlueMakerImplTest extends Specification {

  Path _protoPath

  void setup() {
    // NOOP
  }

  def "Generate:"() {
    given:
    _protoPath = Paths.get('some', 'where', 'Protocal.proto')

    when:
    generate()

    then:

  }

  void generate() {
    new ProtobufGlueMakerImpl().make()
  }
}
