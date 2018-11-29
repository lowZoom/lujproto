package luj.proto.maven.plugin.generate.protoc

import spock.lang.Specification

import java.nio.file.Path
import java.nio.file.Paths

class ProtocFindOrInstallerImplTest extends Specification {

  String _protobufVer
  List _children

  void setup() {
    // NOOP
  }

  def "FindOrInstall:无需安装"() {
    given:
    _protobufVer = '3.5.1'
    _children = [
        ['dir', 'protoc'],
        ['dir', 'protoc-3.5.1-win32'],
        ['file', 'protoc.exe'],
    ]

    when:
    def result = findOrInstall()

    then:
    result == Paths.get('protoc-3.5.1-win32')
  }

  Path findOrInstall() {
    return new ProtocFindOrInstallerImpl(mockEnvDir()).findOrInstall()
  }

  def mockEnvDir() {
    def mock = Stub(ProtocFindOrInstallerImpl.EnvDir)
    mock.list() >> { _children.collect { mockChild(it) }.stream() }
    mock.getProtobufVersion() >> { _protobufVer }
    return mock
  }

  def mockChild(List value) {
    def mock = Stub(ProtocFindOrInstallerImpl.Child)
    mock.isDirectory() >> { value[0] == 'dir' }
    mock.getName() >> { value[1] }
    mock.findProtocExe() >> { Paths.get(value[1]) }
    return mock
  }
}
