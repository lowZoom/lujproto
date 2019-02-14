package luj.proto.maven.plugin.generate.protoimpl

import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.data.type.impl.Data
import luj.data.type.impl.Impl

import javax.lang.model.element.Modifier

@PackageScope
class ProtoImplGeneratorImpl implements ProtoImplGenerator {

  ProtoImplGeneratorImpl(ProtoType protoType, ClassSaver classSaver) {
    _protoType = protoType
    _classSaver = classSaver
  }

  @Override
  ImplementationType generate() {
    List<FieldSpec> fieldList = _protoType.getFieldList().collect { makeField(it) }

    TypeSpec implmentationType = TypeSpec.classBuilder(_protoType.typeName + 'Impl')
        .addModifiers(Modifier.FINAL)
        .superclass(Data)
        .addSuperinterface(getProtoInterface())
        .addAnnotation(makeSuppressWarn('unchecked'))
        .addFields(fieldList)
        .addMethods(fieldList.collect { makeFieldGetter(it) })
        .addMethod(makeToString())
        .build()

    _classSaver.saveInProtoPackage(implmentationType)
    return new ImplementationTypeImpl(_protoType.packageName, implmentationType.name)
  }

  private ClassName getProtoInterface() {
    return ClassName.get(_protoType.packageName, _protoType.typeName)
  }

  private AnnotationSpec makeSuppressWarn(String warn) {
    return AnnotationSpec.builder(SuppressWarnings)
        .addMember('value', /"$warn"/)
        .build()
  }

  private FieldSpec makeField(ProtoField field) {
    return FieldSpec.builder(field.type, '_' + field.name)
        .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
        .initializer('new $T()', field.type)
        .build()
  }

  private MethodSpec makeFieldGetter(FieldSpec field) {
    return overrideBuilder(field.name.substring(1))
        .returns(field.type)
        .addStatement('return $L', field.name)
        .build()
  }

  private MethodSpec makeToString() {
    return overrideBuilder('toString')
        .returns(String)
        .addStatement('return $T.get(this).toString()', Impl)
        .build()
  }

  private MethodSpec.Builder overrideBuilder(String name) {
    return MethodSpec.methodBuilder(name)
        .addAnnotation(Override)
        .addModifiers(Modifier.PUBLIC)
  }

  interface ProtoType {

    String getTypeName()

    String getPackageName()

    List<ProtoField> getFieldList()
  }

  interface ProtoField {

    String getName()

    TypeName getType()
  }

  interface ClassSaver {

    void saveInProtoPackage(TypeSpec implType)
  }

  private final ProtoType _protoType

  private final ClassSaver _classSaver
}
