package omar.dynamic.pojo;

import com.google.gson.JsonElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UploadAllJsonFiles{

    private static final String RESOURCES = "\\resources";
    private static final String PREFIX = System.getProperty("user.dir");
    private static final String PATH = PREFIX + RESOURCES;

    public static List<JsonElement> getAllJsonFiles(){

        List<JsonElement> jsonElements = new ArrayList<>();

        IUploadFile<JsonElement> jsonElementIUploadFile = new UploadJsonFile();

        File file = new File(PATH);

        for (File iteratedFile : Objects.requireNonNull(file.listFiles())){
            if (iteratedFile.getPath().endsWith(".json")) {
                jsonElements.add(jsonElementIUploadFile.uploadFile(iteratedFile));
            }
        }

        return jsonElements;
    }

}
