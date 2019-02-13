package luj.proto.maven.plugin.generate.protocodec

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.proto.internal.meta.spring.ProtoStateCodec
import org.springframework.stereotype.Component

import javax.lang.model.element.Modifier

@PackageScope
class ProtoCodecGeneratorImpl implements ProtoCodecGenerator {

  ProtoCodecGeneratorImpl(ProtoType protoType, ClassName stateType) {
    _protoType = protoType
    _stateType = stateType
  }

  @Override
  void generate() {
    _protoType.saveInSamePackage(makeCodecType())
  }

  private TypeSpec makeCodecType() {
    return TypeSpec.classBuilder(_protoType.protoName + 'Codec')
        .addModifiers(Modifier.FINAL)
        .addAnnotation(Component)
        .addSuperinterface(getCodecInterface())
        .addMethod(makeEncode())
        .addMethod(makeDecode())
        .build()
  }

  private TypeName getCodecInterface() {
    ClassName protoClass = ClassName.get(_protoType.packageName, _protoType.protoName)
    return ParameterizedTypeName.get(ClassName.get(ProtoStateCodec), protoClass)
  }

  private MethodSpec makeEncode() {
    String param = 'protoState'
    return overrideBuilder('encode')
        .returns(byte[])
        .addParameter(Object, param)
        .addStatement('return (($T)$L).build().toByteArray()', _stateType, param)
        .build()
  }

  private MethodSpec makeDecode() {
    String param = 'data'
    return overrideBuilder('decode')
        .returns(Object)
        .addParameter(byte[], param)
        .addException(IOException)
        .addStatement('return $T.parseFrom($L)', _stateType.enclosingClassName(), param)
        .build()
  }

  private MethodSpec.Builder overrideBuilder(String name) {
    return MethodSpec.methodBuilder(name)
        .addAnnotation(Override)
        .addModifiers(Modifier.PUBLIC)
  }

  interface ProtoType {

    String getPackageName()

    String getProtoName()

    void saveInSamePackage(TypeSpec type)
  }

  private final ProtoType _protoType

  private final ClassName _stateType
}
