package luj.proto.internal.meta.spring;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface ProtoPropertyList<PROTO> {

  static <T, R> Function<T, R> f(Function<T, R> f) {
    return f;
  }

  static <T, U> BiConsumer<T, U> f(BiConsumer<T, U> f) {
    return f;
  }
}
