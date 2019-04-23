package luj.proto.maven.plugin;

import luj.proto.anno.Proto;
import luj.proto.maven.plugin.generate.ProtoAllGenerator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * @see <a href="https://github.com/groovy/groovy-eclipse/wiki/Groovy-Eclipse-Maven-plugin#do-almost-nothing">本类存在原因</a>
 */
@Mojo(name = "protoc", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
final class ProtocMojo extends AbstractMojo {

  @Parameter(defaultValue = "${project}", readonly = true)
  private MavenProject project;

  @Override
  public void execute() {
//    ProtoCompiler compiler = ProtoCompiler.Factory.create(project);
//    compiler.compile();

    ProtoAllGenerator.Factory
        .create(Proto.class, project, null, null, getLog())
        .generate();
  }
}
