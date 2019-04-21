package init.build.git

import core.AutoCtor

@AutoCtor
class AllModuleCloner {

  void cloneAll() {
    File buildGit = new GitRootFinder(_buildPath).find()
    String projRoot = buildGit.parentFile.absolutePath

    new AntBuilder().with {
      exec(executable: 'git', failonerror: true) {
        arg(value: '--version')
      }
    }

    new ModuleCloneTo(projRoot)
        .from('https://github.com/lowZoom/lujparent.git')
        .from('https://github.com/lowZoom/lujava.git')
        .from('https://github.com/lowZoom/lujdata.git')
        .from('https://github.com/lowZoom/lujgenerate.git')
        .from('https://github.com/lowZoom/lujtest.git')
  }

  private String _buildPath
}
