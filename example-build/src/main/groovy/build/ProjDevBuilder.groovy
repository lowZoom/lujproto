package build

import build.clean.BuildEnvCleaner
import build.git.AllModuleCloner
import core.AutoCtor

@AutoCtor
class ProjDevBuilder {

  void build() {
    new BuildEnvCleaner(_buildPath).clean()

    new AllModuleCloner(_buildPath).cloneAll()
  }

  private String _buildPath
}
