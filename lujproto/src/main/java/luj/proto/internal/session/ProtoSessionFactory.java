package luj.proto.internal.session;

import luj.proto.api.ProtoSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface ProtoSessionFactory {

  static ProtoSessionFactory get() {
    try (AnnotationConfigApplicationContext appCtx =
        new AnnotationConfigApplicationContext(SpringConfig.class)) {
      return appCtx.getBean(ProtoSessionFactory.class);
    }
  }

  ProtoSession create(ApplicationContext springContext);
}
