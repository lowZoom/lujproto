package luj.proto.maven.plugin.generate.util.maven

import java.nio.file.Path

interface MavenPath {

  Path getSrcMainJava()

  Path getTargetGeneratedsourcesLujproto()

  Path getBuildEnv()
}
