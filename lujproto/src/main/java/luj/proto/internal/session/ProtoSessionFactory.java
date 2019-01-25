package luj.proto.internal.session;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.proto.api.ProtoSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public interface ProtoSessionFactory {

  @SuppressWarnings("ZeroLengthArrayAllocation")
  static ProtoSessionFactory get(List<Class<?>> moreConf) {
    Class<?>[] confList = ImmutableList.<Class<?>>builder()
        .add(SpringConfig.class)
        .addAll(moreConf)
        .build()
        .toArray(new Class<?>[0]);

    try (GenericApplicationContext appCtx = new AnnotationConfigApplicationContext(confList)) {
      return appCtx.getBean(ProtoSessionFactory.class);
    }
  }

  ProtoSession create(ApplicationContext springContext);
}
