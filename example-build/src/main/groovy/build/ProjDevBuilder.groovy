package build

import build.clean.BuildEnvCleaner
import core.AutoCtor

import java.nio.file.Paths

@AutoCtor
class ProjDevBuilder {

  void build() {
    new BuildEnvCleaner(Paths.get(_buildPath, 'env').toString()).clean()
  }

  private String _buildPath
}
