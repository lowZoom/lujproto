package init.build

import core.AutoCtor
import init.build.clean.BuildEnvCleaner
import init.build.git.AllModuleCloner

@AutoCtor
class ProjDevBuilder {

  void build() {
    new BuildEnvCleaner(_buildPath).clean()

    new AllModuleCloner(_buildPath).cloneAll()
  }

  private String _buildPath
}
