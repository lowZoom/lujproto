package luj.proto.maven.plugin.generate.protoimpl

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope

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
        .addSuperinterface(getProtoInterface())
        .addFields(fieldList)
        .addMethods(fieldList.collect { makeFieldGetter(it) })
        .build()

    _classSaver.saveInProtoPackage(implmentationType)
    return new ImplementationTypeImpl(_protoType.packageName, implmentationType.name)
  }

  private ClassName getProtoInterface() {
    return ClassName.get(_protoType.packageName, _protoType.typeName)
  }

  private FieldSpec makeField(ProtoField field) {
    return FieldSpec.builder(field.type, '_' + field.name)
        .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
        .initializer('new $T()', field.type)
        .build()
  }

  private MethodSpec makeFieldGetter(FieldSpec field) {
    return MethodSpec.methodBuilder(field.name.substring(1))
        .addAnnotation(Override)
        .addModifiers(Modifier.PUBLIC)
        .returns(field.type)
        .addStatement('return $L', field.name)
        .build()
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
