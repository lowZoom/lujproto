package luj.proto.internal.session;

import luj.ava.spring.Internal;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
    "luj.proto",
}, includeFilters = {
    @ComponentScan.Filter(Internal.class),
})
final class SpringConfig {
  // NOOP
}
