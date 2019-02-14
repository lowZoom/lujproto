package luj.proto.internal.meta.property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.ava.spring.Internal;
import luj.data.type.impl.Data;
import luj.proto.internal.meta.spring.ProtoPropertyList;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class PropertyListFactoryImpl implements PropertyListFactory {

  @Override
  public List<ProtoProperty> create(ProtoPropertyList<?> holder) {
    return Arrays.stream(holder.getClass().getDeclaredMethods())
        .map(m -> createProperty(holder, m))
        .collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  private ProtoProperty createProperty(ProtoPropertyList<?> holder, Method propMethod) {
    Object[] info = getPropertyInfo(holder, propMethod);
    Function<Object, Data> dataGetter = (Function<Object, Data>) info[0];

    List<Type> typeArgs = _propTypeArgsGetter.getTypeArgs(holder.getClass(), propMethod.getName());
    return new ProtoPropertyImpl(dataGetter, info[1], info[2], typeArgs);
  }

  private Object[] getPropertyInfo(ProtoPropertyList<?> holder, Method propMethod) {
    try {
      propMethod.setAccessible(true);
      return (Object[]) propMethod.invoke(holder);

    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  @Autowired
  private PropTypeArgsGetter _propTypeArgsGetter;
}
