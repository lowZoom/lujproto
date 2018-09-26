package build.git

import core.AutoCtor

@AutoCtor
class AllModuleCloner {

  void cloneAll() {
    File buildGit = new GitRootFinder(_buildPath).find()
    String projRoot = buildGit.parentFile.absolutePath

    new AntBuilder().with {
      exec(executable: 'git', failonerror: 'true') {
        arg(value: "--version")
      }
    }

    new GitCloneTo(projRoot)
        .from('https://github.com/lowZoom/lujparent.git')
  }

  private String _buildPath
}
