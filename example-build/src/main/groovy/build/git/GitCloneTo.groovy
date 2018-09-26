package build.git

import build.git.cmd.GitCmd
import core.AutoCtor
import groovy.transform.PackageScope

import java.nio.file.Paths

@PackageScope
@AutoCtor
class GitCloneTo {

  GitCloneTo from(String url) {
    String repoDir = getDirName(url)
    String repoPath = Paths.get(_projRoot, repoDir)

    new GitCmd().clone(url).to(repoPath)
    return this
  }

  private String getDirName(String url) {
    String urlBase = url.replaceFirst('/+$', '').split('/')[-1]
    return urlBase.split(/\./, 2)[0]
  }

  private String _projRoot
}
