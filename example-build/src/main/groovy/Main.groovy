import build.ProjDevBuilder

String buildPath = args ? args[0]
    : /E:\luj\code\luj\lujproto\example-build\bin/

new ProjDevBuilder(buildPath).build()

sleep(100)
