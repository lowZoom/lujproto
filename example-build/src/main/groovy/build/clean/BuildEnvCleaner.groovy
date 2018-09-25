package build.clean

import core.AutoCtor

import java.nio.file.Paths

@AutoCtor
class BuildEnvCleaner {

  void clean() {
    String tempPath = Paths.get(_envPath, 'temp')

    new AntBuilder().with {
      echo('清理构建环境...')
      echo(tempPath)
//      delete(dir: tempPath, verbose: 'true')
    }
  }

  private String _envPath
}
