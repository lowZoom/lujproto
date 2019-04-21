package init.build.git.cmd

import core.AutoCtor
import groovy.transform.PackageScope

@PackageScope
@AutoCtor
class CloneImpl implements GitCmd.Clone {

  @Override
  void to(String localPath) {
    new AntBuilder().with {
      exec(executable: 'git', failonerror: 'true') {
        arg(value: 'clone')
        arg(value: _repoUrl)
        arg(value: localPath)
        arg(value: '--verbose')
      }
    }
  }

  private String _repoUrl
}
