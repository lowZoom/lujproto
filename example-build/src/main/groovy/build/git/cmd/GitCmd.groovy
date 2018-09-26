package build.git.cmd

class GitCmd {

  interface Clone {

    void to(String localPath)
  }

  Clone clone(String url) {
    return new CloneImpl(url)
  }
}
