package maven.install

class Main {

  static void main(String[] args) {
    String startPath = args ? args[0]
        : 'C:\\ssd\\work\\luj\\lujproto\\example-build\\bin'

    new ProtoMavenInstaller(startPath).install()
  }
}
