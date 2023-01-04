package omar.dynamic.pojo;


import com.squareup.javapoet.JavaFile;
import org.drools.compiler.compiler.io.memory.MemoryFileSystem;

import java.util.List;

public class Main{

    public static void main(String[] args) {

        PojoCreator creator = new PojoCreator();
        List<JavaFile> javaFiles = creator.getPojoJavaFiles();

        MemoryFileSystem generatorMemoryFileSystem = new MemoryFileSystem();
        ClassGenerator classGenerator = new ClassGenerator();
        ClassLoader generatedClassesClassLoader = classGenerator.compile(generatorMemoryFileSystem, javaFiles);

        try {
            for (JavaFile javaFile : javaFiles){
                Object object = generatedClassesClassLoader.loadClass(VCB.PACKAGE_NAME + "." + javaFile.typeSpec.name).newInstance();
                System.out.println(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
