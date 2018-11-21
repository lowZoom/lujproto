package luj.proto.anno.proc.proto.protobuf;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.processing.Filer;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import luj.generate.annotation.process.AnnoProc;
import luj.generate.annotation.process.type.ProcType;

final class MessageImpl implements ProtoFileGeneratorImpl.ProtobufMessage {

  MessageImpl(ProcType.Package aPackage, String messageName,
      TypeElement elem, Filer filer, AnnoProc.Log log) {
    _package = aPackage;
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
    return _package.getName();
  }

  @Override
  public String getMessageName() {
    return _messageName;
  }

  @Override
  public List<ProtoFileGeneratorImpl.MessageField> getFieldList() {
    return _elem.getEnclosedElements().stream()
        .map(e -> new FieldImpl((ExecutableElement) e, new TypeMapImpl()))
        .collect(Collectors.toList());
  }

  @Override
  public ProtoFileGeneratorImpl.ProtoWriter openWriter() throws IOException {
    String fileName = _messageName + ".proto";
    _log.warn(fileName);

    FileObject fileObj = _filer.createResource(
        StandardLocation.SOURCE_OUTPUT, getPackage(), fileName, _elem);

    return new WriterImpl(toWriter(fileObj.openOutputStream()));
  }

  private BufferedWriter toWriter(OutputStream out) {
    return new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
  }

  private final ProcType.Package _package;
  private final String _messageName;

  private final TypeElement _elem;
  private final Filer _filer;

  private final AnnoProc.Log _log;
}
