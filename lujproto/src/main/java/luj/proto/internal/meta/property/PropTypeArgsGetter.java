package luj.proto.internal.meta.property;

import com.google.common.collect.ImmutableList;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import luj.ava.reflect.generic.GenericType;
import luj.ava.spring.Internal;

@Internal
final class PropTypeArgsGetter {

  List<Type> getTypeArgs(Class<?> holderType, String propName) {
    Class<?> protoType = GenericType.of(holderType.getGenericInterfaces()[0]).getTypeArg(0);
    Method fieldMethod = getFieldMethod(protoType, propName);

    if (fieldMethod.getReturnType().getTypeParameters().length <= 0) {
      return ImmutableList.of();
    }

    ParameterizedType fieldType = (ParameterizedType) fieldMethod.getGenericReturnType();
    return ImmutableList.copyOf(fieldType.getActualTypeArguments());
  }

  private Method getFieldMethod(Class<?> protoType, String fieldName) {
    try {
      return protoType.getMethod(fieldName);

    } catch (NoSuchMethodException e) {
      throw new UnsupportedOperationException(e);
    }
  }
}
