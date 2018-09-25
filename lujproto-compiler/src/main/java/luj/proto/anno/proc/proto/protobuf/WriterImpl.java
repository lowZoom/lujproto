package luj.proto.anno.proc.proto.protobuf;

import java.io.BufferedWriter;
import java.io.IOException;
import luj.proto.anno.proc.proto.protobuf.ProtoFileGeneratorImpl.ProtoWriter;

final class WriterImpl implements ProtoWriter {

  WriterImpl(BufferedWriter bufWriter) {
    _bufWriter = bufWriter;
  }

  @Override
  public void line(String format, Object... args) throws IOException {
    _bufWriter.write(String.format(format, args));

    line();
  }

  @Override
  public void line() throws IOException {
    _bufWriter.write("\n");
  }

  @Override
  public void close() throws IOException {
    _bufWriter.close();
  }

  private final BufferedWriter _bufWriter;
}
