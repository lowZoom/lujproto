package luj.proto.maven.plugin.compile

import groovy.transform.PackageScope
import luj.groovy.AutoCtor
import org.apache.maven.project.MavenProject

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors

@PackageScope
@AutoCtor
class ProjectRootImpl implements ProtoCompilerImpl.ProjectRoot {

  @Override
  ProtoCompilerImpl.ProtoList findProto() {
    String targetPath = _project.build.directory
    String genRoot = Paths.get(targetPath, 'generated-sources')

    Path protoRoot = Paths.get(genRoot, 'annotations')
    List<String> protoList = findImpl(protoRoot)

    def project = new ProjectImpl(_project, protoRoot)
    return new ProtoListImpl(protoList, project)
  }

  private List<String> findImpl(Path searchRoot) {
    if (!Files.isDirectory(searchRoot)) {
      return []
    }

    return Files.walk(searchRoot)
        .filter { Files.isRegularFile(it) }
        .map { it.toString() }
        .filter { String p -> p.endsWith('.proto') }
        .collect(Collectors.toList())
  }

  private MavenProject _project
}
