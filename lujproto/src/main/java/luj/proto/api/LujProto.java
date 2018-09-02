package luj.proto.api;

import luj.proto.internal.session.ProtoSessionFactory;
import org.springframework.context.ApplicationContext;

public enum LujProto {
  ;

  public static ProtoSession start(ApplicationContext springContext) {
    return ProtoSessionFactory.get().create(springContext);
  }
}
