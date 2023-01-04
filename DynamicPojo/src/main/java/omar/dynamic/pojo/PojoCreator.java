package omar.dynamic.pojo;
import com.google.gson.JsonElement;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.JavaFile;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PojoCreator{

    public List<JavaFile> getPojoJavaFiles() {

        List<JsonElement> jsonElements = UploadAllJsonFiles.getAllJsonFiles();
        List<JavaFile> javaFiles = new ArrayList<>();

        for (JsonElement jsonElement : jsonElements) {

            DataHolder dataHolder = new DataHolder();
            dataHolder.parse(jsonElement);

            Collection<MethodSpec> methodSpecs = new ArrayList<>();
            Collection<FieldSpec> fieldSpecs = new ArrayList<>();
            for (Field field : dataHolder.getFields()) {
                methodSpecs.add(getterMethods(field));
                fieldSpecs.add(getFieldSpec(field));
            }

            //methodSpecs.add(getToStringOverride(dataHolder.getFields().get(0)));

            TypeSpec typeSpec = TypeSpec.classBuilder(dataHolder.getId())
                    .addModifiers(Modifier.PUBLIC)
                    .addMethods(methodSpecs)
                    .addFields(fieldSpecs)
                    .build();

            JavaFile javaFile = JavaFile.builder(VCB.PACKAGE_NAME, typeSpec)
                    .build();

            javaFiles.add(javaFile);
        }
        return javaFiles;
    }

    private MethodSpec getToStringOverride(Field field) {
        return MethodSpec.methodBuilder("toString")
                .addModifiers(Modifier.PUBLIC)
                .addStatement(
                        "return String.valueOf(this." + field.getName() + ")"
                )
                .returns(String.class)
                .build();
    }

    private FieldSpec getFieldSpec(Field field){

        FieldSpec fieldSpec = null;
        try{
            fieldSpec = FieldSpec
                    .builder(Class.forName(field.getType()), field.getName(), Modifier.PUBLIC)
                    .build();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return fieldSpec;
    }

    private MethodSpec getterMethods(Field field){

        MethodSpec getterMethodSpec = null;
        try {
            getterMethodSpec = MethodSpec.methodBuilder(field.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("return this." + field.getName())
                    .returns(Class.forName(field.getType()))
                    .build();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return getterMethodSpec;
    }
}
