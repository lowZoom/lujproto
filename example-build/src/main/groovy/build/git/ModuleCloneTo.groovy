package build.git

import build.git.cmd.GitCmd
import core.AutoCtor
import core.ant.AntCmd
import groovy.transform.PackageScope

import java.nio.file.Files
import java.nio.file.Paths

@PackageScope
@AutoCtor
class ModuleCloneTo {

  ModuleCloneTo from(String url) {
    String repoDir = getDirName(url)
    String repoPath = Paths.get(_projRoot, repoDir)

    cloneImpl(url, repoPath)
    return this
  }

  private String getDirName(String url) {
    return url.replaceFirst('/+$', '')
        .replaceFirst(/\.git$/, '')
        .split('/')[-1]
  }

  private void cloneImpl(String url, String repoPath) {
    if (!shouldClone(repoPath)) {
      AntCmd.create().echo("仓库已存在，跳过 -> $repoPath")
      return
    }
    new GitCmd().clone(url).to(repoPath)
  }

  private boolean shouldClone(String repoPath) {
    return !Files.isRegularFile(Paths.get(repoPath, '.git', 'HEAD'))
  }

  private String _projRoot
}
