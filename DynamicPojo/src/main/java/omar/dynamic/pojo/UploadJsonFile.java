package omar.dynamic.pojo;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import omar.dynamic.pojo.exception.FileReaderException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UploadJsonFile implements IUploadFile<JsonElement>{

    @Override
    public JsonElement uploadFile(File file) {

        if (!file.canRead()){
            System.out.println(("Can't read from file path " + file.getPath()));
            throw new FileReaderException("Can't read from file");
        }
        StringBuilder allFile = new StringBuilder();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                allFile.append(st);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return JsonParser.parseString(allFile.toString());
    }
}
