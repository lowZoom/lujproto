package luj.proto.maven.plugin.generate.protoconstruct

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.proto.internal.meta.spring.ProtoConstructor
import org.springframework.stereotype.Component

import javax.lang.model.element.Modifier

@PackageScope
class ProtoConstructGeneratorImpl implements ProtoConstructGenerator {

  ProtoConstructGeneratorImpl(ProtoType protoType, ClassSaver classSaver) {
    _protoType = protoType
    _classSaver = classSaver
  }

  @Override
  void generate() {
    _classSaver.saveInProtoPackage(makeConstructorType())
  }

  private TypeSpec makeConstructorType() {
    ClassName protoClass = ClassName.get(_protoType.packageName, _protoType.protoName)
    return TypeSpec.classBuilder(protoClass.simpleName() + 'Construct')
        .addModifiers(Modifier.FINAL)
        .addAnnotation(Component)
        .addSuperinterface(getConstructorInterface(protoClass))
        .addMethod(makeConstruct(protoClass))
        .addMethod(makeConstructState())
        .build()
  }

  private TypeName getConstructorInterface(ClassName protoClass) {
    return ParameterizedTypeName.get(ClassName.get(ProtoConstructor), protoClass)
  }

  private MethodSpec makeConstruct(ClassName protoClass) {
    return overrideBuilder('construct')
        .returns(protoClass)
        .addStatement('return new $T()', _protoType.getImplementationType())
        .build()
  }

  private MethodSpec makeConstructState() {
    def builder = overrideBuilder('constructState').returns(Object)
    _protoType.makeStateConstructMethod(builder)
    return builder.build()
  }

  private MethodSpec.Builder overrideBuilder(String name) {
    return MethodSpec.methodBuilder(name)
        .addAnnotation(Override)
        .addModifiers(Modifier.PUBLIC)
  }

  interface ProtoType {

    String getPackageName()

    String getProtoName()

    TypeName getImplementationType()

    void makeStateConstructMethod(MethodSpec.Builder methodBuilder)
  }

  interface ClassSaver {

    void saveInProtoPackage(TypeSpec constructType)
  }

  private final ProtoType _protoType

  private final ClassSaver _classSaver
}
