package luj.proto.anno.proc.proto.protobuf;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

final class ProtoFileGeneratorImpl implements ProtoFileGenerator {

  public interface ProtobufMessage {

    String getSyntax();

    String getPackage();

    String getMessageName();

    List<MessageField> getFieldList();

    ProtoWriter openWriter() throws IOException;
  }

  interface MessageField {

    String getType();

    String getName();
  }

  interface ProtoWriter extends Closeable {

    void line(String format, Object... args) throws IOException;

    void line() throws IOException;
  }

  ProtoFileGeneratorImpl(ProtobufMessage protoType) {
    _protoType = protoType;
  }

  @Override
  public void generate() throws IOException {
    ProtobufMessage message = _protoType;
    try (ProtoWriter writer = message.openWriter()) {
      writeProto(writer, message);
    }

//    _protoType.saveToFile(typeName + ".proto");
  }

  private void writeProto(ProtoWriter writer, ProtobufMessage message) throws IOException {
    writer.line("syntax=\"%s\";", message.getSyntax());
    writer.line();

    writer.line("package %s;", message.getPackage());
    writer.line();

    writer.line("message %s {", message.getMessageName());

    List<MessageField> fieldList = message.getFieldList();
    for (int i = 0, n = fieldList.size(); i < n; i++) {
      MessageField field = fieldList.get(i);
      writer.line("%s %s = %s;", field.getType(), field.getName(), i);
    }

    writer.line("}");
  }

  private final ProtobufMessage _protoType;
}
