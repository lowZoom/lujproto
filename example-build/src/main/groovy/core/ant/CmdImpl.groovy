package core.ant

import core.AutoCtor
import groovy.transform.PackageScope

import java.nio.charset.StandardCharsets

@PackageScope
@AutoCtor
class CmdImpl extends AntCmd {

  def echo(String msg) {
    return _ant.echo(new String(msg.bytes, StandardCharsets.UTF_8))
  }

  @Override
  def methodMissing(String name, Object args) {
    return _ant.invokeMethod(name, args)
  }

  private AntBuilder _ant
}
