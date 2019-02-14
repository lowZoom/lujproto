package luj.proto.maven.plugin.generate.protoprop

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import groovy.transform.PackageScope
import luj.proto.internal.meta.spring.ProtoPropertyList
import org.springframework.stereotype.Component

import javax.lang.model.element.Modifier

@PackageScope
class ProtoPropGeneratorImpl implements ProtoPropGenerator {

  ProtoPropGeneratorImpl(ProtoType protoType, ClassSaver classSaver) {
    _protoType = protoType
    _classSaver = classSaver
  }

  @Override
  void generate() {
    _classSaver.saveInProtoPackage(makeListType())
  }

  private TypeSpec makeListType() {
    ClassName protoClass = ClassName.get(_protoType.packageName, _protoType.protoName)
    return TypeSpec.classBuilder(_protoType.protoName + 'Prop')
        .addModifiers(Modifier.FINAL)
        .addAnnotation(Component)
        .addSuperinterface(getPropListInterface(protoClass))
        .addMethods(_protoType.fieldList.collect { makeProperty(protoClass, it) })
        .build()
  }

  private TypeName getPropListInterface(ClassName protoClass) {
    return ParameterizedTypeName.get(ClassName.get(ProtoPropertyList), protoClass)
  }

  private MethodSpec makeProperty(ClassName protoClass, ProtoField field) {
    ClassName stateType = _protoType.getStateType()
    String fieldNameCap = field.name.capitalize()

    return MethodSpec.methodBuilder(field.name)
        .returns(Object[])
        .addCode('return new $T[]{\n', Object)
        .addCode('$T.getter($T::$L),\n', ProtoPropertyList, protoClass, field.name)
        .addCode('$T.setter($T::set$L, $L),\n', ProtoPropertyList, stateType, fieldNameCap, field.stateGetter)
        .addCode('$T.getter($L),\n', ProtoPropertyList, field.stateGetter)
        .addStatement('}')
        .build()
  }

  interface ProtoType {

    String getPackageName()

    String getProtoName()

    List<ProtoField> getFieldList()

    TypeName getStateType()
  }

  interface ProtoField {

    String getName()

    String getStateGetter()
  }

  interface ClassSaver {

    void saveInProtoPackage(TypeSpec listType)
  }

  private final ProtoType _protoType

  private final ClassSaver _classSaver
}
