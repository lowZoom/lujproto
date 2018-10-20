package luj.proto.maven.plugin.compile

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class InstalledProtocImpl implements ProtoListImpl.InstalledProtoc {

  @Override
  void compile(String protoPath) {
    assert new File(protoPath).exists()
    println("$_protocExe --java_out=$_outputPath $protoPath")

//    new AntBuilder().with {
//      mkdir(dir: _outputPath)
//
//      String subpath = protoPath.replace(_protoRoot, '')
//          .replaceAll($/^\\/$, '')
//          .replaceAll('^/', '')
//
//      exec(executable: _protocExe, failonerror: 'true') {
//        arg(value: "--proto_path=$_protoRoot")
//        arg(value: "--java_out=$_outputPath")
//        arg(value: protoPath)
//      }
//    }
  }

  private String _protocExe

  private String _protoRoot
  private String _outputPath
}
