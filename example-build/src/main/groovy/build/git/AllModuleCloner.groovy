package build.git

import core.AutoCtor

@AutoCtor
class AllModuleCloner {

  void cloneAll() {
    File buildGit = new GitRootFinder(_buildPath).find()
    String projRoot = buildGit.parentFile.absolutePath

    println(projRoot)
  }

  private String _buildPath
}
