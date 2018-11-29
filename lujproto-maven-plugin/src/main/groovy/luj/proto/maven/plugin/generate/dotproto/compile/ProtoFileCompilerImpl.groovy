package luj.proto.maven.plugin.generate.dotproto.compile

import groovy.transform.PackageScope

import java.nio.file.Files
import java.nio.file.Path

@PackageScope
class ProtoFileCompilerImpl implements ProtoFileCompiler {

  ProtoFileCompilerImpl(Path dotProto, String protocExe, String protoRoot, String outputPath) {
    _dotProto = dotProto
    _protocExe = protocExe

    _protoRoot = protoRoot
    _outputPath = outputPath
  }

  @Override
  void compile() {
    assert Files.isRegularFile(_dotProto)
//    println("$_protocExe --java_out=$_outputPath $protoPath")

    new AntBuilder().with {
      mkdir(dir: _outputPath)

      exec(executable: _protocExe, failonerror: true) {
        arg(value: "--proto_path=$_protoRoot")
        arg(value: "--java_out=$_outputPath")
        arg(value: _dotProto.toString())
      }
    }
  }

  private final Path _dotProto
  private final String _protocExe

  private final String _protoRoot
  private final String _outputPath
}
