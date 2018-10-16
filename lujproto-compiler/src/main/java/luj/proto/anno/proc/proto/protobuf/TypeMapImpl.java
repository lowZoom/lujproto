package luj.proto.anno.proc.proto.protobuf;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import luj.data.type.JStr;

final class TypeMapImpl implements FieldImpl.TypeMap {

  @Override
  public String getProtoType(String javaType) {
    return MAP.get(javaType);
  }

  private static ImmutableMap.Builder<String, String> mapBuilder() {
    return ImmutableMap.builder();
  }

  private static String key(Class<?> keyType) {
    return keyType.getSimpleName();
  }

  private static final Map<String, String> MAP = mapBuilder()
      .put(key(JStr.class), "string")
      .build();
}
