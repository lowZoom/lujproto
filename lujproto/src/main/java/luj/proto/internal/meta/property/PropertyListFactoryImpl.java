package luj.proto.internal.meta.property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.ava.spring.Internal;
import luj.data.type.impl.Data;
import luj.proto.internal.meta.spring.ProtoPropertyList;

@Internal
final class PropertyListFactoryImpl implements PropertyListFactory {

  @Override
  public List<ProtoProperty> create(ProtoPropertyList<?> holder) {
    return Arrays.stream(holder.getClass().getDeclaredMethods())
        .map(m -> getPropertyInfo(holder, m))
        .map(this::createProperty)
        .collect(Collectors.toList());
  }

  private Object[] getPropertyInfo(ProtoPropertyList<?> holder, Method propMethod) {
    try {
      propMethod.setAccessible(true);
      return (Object[]) propMethod.invoke(holder);

    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  @SuppressWarnings("unchecked")
  private ProtoProperty createProperty(Object[] info) {
    return new ProtoPropertyImpl(
        (Function<Object, Data>) info[0],
        info[1]
    );
  }
}
