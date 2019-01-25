package luj.proto.example.core.lujproto;

import com.google.protobuf.MessageLite;
import luj.proto.internal.meta.spring.ProtoCodec;
import org.springframework.stereotype.Component;

@Component
final class ExampleEncoder implements ProtoCodec<Object> {

  @Override
  public byte[] encode(Object protoState) {
    return ((MessageLite) protoState).toByteArray();
  }
}
