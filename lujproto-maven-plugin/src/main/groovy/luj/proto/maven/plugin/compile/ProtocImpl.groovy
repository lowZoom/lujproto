package luj.proto.maven.plugin.compile

import groovy.transform.PackageScope

@PackageScope
class ProtocImpl implements ProtoListImpl.Protoc {

  ProtocImpl(String protocExe, String protoRoot, String outputPath) {
    _protocExe = protocExe

    _protoRoot = protoRoot
    _outputPath = outputPath
  }

  @Override
  ProtoListImpl.ProtobufClass compile(String protoPath) {
    assert new File(protoPath).exists()
//    println("$_protocExe --java_out=$_outputPath $protoPath")

    new AntBuilder().with {
      mkdir(dir: _outputPath)

      exec(executable: _protocExe, failonerror: 'true') {
        arg(value: "--proto_path=$_protoRoot")
        arg(value: "--java_out=$_outputPath")
        arg(value: protoPath)
      }
    }

    return new ProtobufClassImpl(protoPath)
  }

  private final String _protocExe

  private final String _protoRoot
  private final String _outputPath
}
