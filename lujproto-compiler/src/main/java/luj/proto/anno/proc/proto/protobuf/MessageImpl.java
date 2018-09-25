package luj.proto.anno.proc.proto.protobuf;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import luj.generate.annotation.process.AnnoProc;
import luj.generate.annotation.process.AnnoProc.Log;
import luj.proto.anno.proc.proto.protobuf.ProtoFileGeneratorImpl.MessageField;
import luj.proto.anno.proc.proto.protobuf.ProtoFileGeneratorImpl.ProtoWriter;
import org.omg.CORBA.NO_IMPLEMENT;

final class MessageImpl implements ProtoFileGeneratorImpl.ProtobufMessage {

  MessageImpl(String packageName, String messageName,
      TypeElement elem, Filer filer, Log log) {
    _packageName = packageName;
    _messageName = messageName;

    _elem = elem;
    _filer = filer;

    _log = log;
  }

  @Override
  public String getSyntax() {
    return "proto3";
  }

  @Override
  public String getPackage() {
    return _packageName;
  }

  @Override
  public String getMessageName() {
    return _messageName;
  }

  @Override
  public List<MessageField> getFieldList() {
    throw new NO_IMPLEMENT("getFieldList尚未实现");
  }

  @Override
  public ProtoWriter openWriter() throws IOException {
    String fileName = _messageName + ".proto";
    _log.warn(fileName);

    FileObject fileObj = _filer.createResource(
        StandardLocation.SOURCE_OUTPUT, _packageName, fileName, _elem);

    return new WriterImpl(toWriter(fileObj.openOutputStream()));
  }

  private BufferedWriter toWriter(OutputStream out) {
    return new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
  }

  private final String _packageName;
  private final String _messageName;

  private final TypeElement _elem;
  private final Filer _filer;

  private final AnnoProc.Log _log;
}
