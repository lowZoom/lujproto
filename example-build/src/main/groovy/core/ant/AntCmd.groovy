package core.ant

abstract class AntCmd {

  static AntCmd create() {
    return new CmdImpl(new AntBuilder())
  }

  abstract def methodMissing(String name, Object args)
}
