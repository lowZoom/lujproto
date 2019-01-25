package luj.proto.api;

import com.google.common.collect.ImmutableList;
import luj.proto.internal.session.ProtoSessionFactory;
import org.springframework.context.ApplicationContext;

public enum LujProto {
  ;

  public static ProtoSession start(ApplicationContext appContext, Class<?>... protoConf) {
    return ProtoSessionFactory.get(ImmutableList.copyOf(protoConf)).create(appContext);
  }
}
