package omar.dynamic.pojo;

import com.squareup.javapoet.JavaFile;
import org.drools.compiler.compiler.io.memory.MemoryFileSystem;
import org.drools.ecj.EclipseJavaCompiler;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.memorycompiler.CompilationResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClassGenerator{

    public ClassLoader compile(MemoryFileSystem memoryFileSystem, List<JavaFile> javaFiles) {
        ClassLoader parentClassLoader = memoryFileSystem.memoryClassLoader(ClassGenerator.class.getClassLoader());
        String[] filesContent = addJavaSources(memoryFileSystem, javaFiles);

        CompilationResult compilationResult = new EclipseJavaCompiler().compile(filesContent, memoryFileSystem, memoryFileSystem, parentClassLoader);
        if (compilationResult.getErrors().length > 0){
            System.out.println("Error in compilation " + Arrays.toString(compilationResult.getErrors()));
        }

        return memoryFileSystem.memoryClassLoader(ClassGenerator.class.getClassLoader());
    }

    private String[] addJavaSources(MemoryFileSystem memoryFileSystem, Collection<JavaFile> sourceFiles) {
        String[] result = new String[sourceFiles.size()];
        return sourceFiles.stream().map(sourceFile -> {
            String sourceFilePath = sourceFile.toJavaFileObject().getName();
            try {
                Resource sourceResource = KieServices.get().getResources().newInputStreamResource(sourceFile.toJavaFileObject().openInputStream())
                        .setResourceType(ResourceType.JAVA);
                memoryFileSystem.write(sourceFilePath, sourceResource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sourceFilePath;
        }).collect(Collectors.toList()).toArray(result);
    }

}
