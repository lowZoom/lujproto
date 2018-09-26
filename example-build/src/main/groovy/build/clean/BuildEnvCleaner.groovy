package build.clean

import core.AutoCtor
import core.ant.AntCmd

import java.nio.file.Paths

@AutoCtor
class BuildEnvCleaner {

  void clean() {
    String tempPath = Paths.get(_buildPath, 'env', 'temp')

    AntCmd.create().with {
      echo('清理构建环境...')
      delete(dir: tempPath, verbose: 'true')
    }
  }

  private String _buildPath
}
