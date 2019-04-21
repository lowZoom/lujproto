package init.build.git

import core.AutoCtor
import groovy.transform.PackageScope

@PackageScope
@AutoCtor
class GitRootFinder {

  File find() {
    def fileCursor = new File(_startPath)

    while (fileCursor) {
      if (isGitRoot(fileCursor)) {
        return fileCursor
      }
      fileCursor = fileCursor.parentFile
    }

    return null
  }

  private boolean isGitRoot(File file) {
    return file.listFiles()
        .findAll { it.directory }
        .findAll { it.name == '.git' }
  }

  private String _startPath
}
