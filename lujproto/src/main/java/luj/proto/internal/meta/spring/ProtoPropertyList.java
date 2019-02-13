package luj.proto.internal.meta.spring;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface ProtoPropertyList<PROTO> {

  static <T, R> Function<T, R> getter(Function<T, R> f) {
    return f;
  }

  static <T, U> BiConsumer<T, U> setter(BiConsumer<T, U> f, Function<T, U> g) {
    return f;
  }
}
