package luj.proto.maven.plugin.generate.maven

import java.nio.file.Path

interface MavenPath {

  Path getSrcMainJava()

  Path getTargetGeneratedsourcesLujproto()

  Path getBuildEnv()
}
