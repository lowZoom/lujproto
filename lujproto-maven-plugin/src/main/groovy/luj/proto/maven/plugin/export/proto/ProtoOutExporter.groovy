package luj.proto.maven.plugin.export.proto


import java.nio.file.Path

class ProtoOutExporter {

  ProtoOutExporter(Path outRoot, Path protoRoot) {
    _outRoot = outRoot
    _protoRoot = protoRoot
  }

  void export() {
    if (!_outRoot) {
      return
    }

    new AntBuilder().with {
      echo('清理导出目录...')
      delete(includeemptydirs: true) {//, verbose: true) {
        fileset(dir: _outRoot, includes: '**/*')
      }

      echo('导出proto文件...')
      copy(todir: _outRoot) {
        fileset(dir: _protoRoot, includes: '**/*.proto')
      }
    }
  }

  private final Path _outRoot

  private final Path _protoRoot
}
