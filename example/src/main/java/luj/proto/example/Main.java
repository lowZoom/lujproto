package luj.proto.example;

import luj.proto.example.module.login.ui.LoginUi;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("luj.proto.example")
final class Main {

  public static void main(String[] args) {

    try (AnnotationConfigApplicationContext appCtx =
        new AnnotationConfigApplicationContext(Main.class)) {
      new Main(appCtx).start();
    }
  }

  private Main(ApplicationContext springContext) {
    _springContext = springContext;
  }

  private void start() {
    LoginUi.create(_springContext).show();
  }

  private final ApplicationContext _springContext;
}
