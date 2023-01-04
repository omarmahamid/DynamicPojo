package omar.dynamic.pojo;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class DataHolder{

    private String id;
    private List<Field> fields;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void parse(JsonElement jsonElement) {
        setId(jsonElement.getAsJsonObject().get("id").getAsString());
        int size =
                jsonElement.getAsJsonObject().get("fields").getAsJsonArray().size();

        fields = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Field field = new Field();
            field.setName(jsonElement.getAsJsonObject().get("fields").getAsJsonArray().get(i).getAsJsonObject().get("name").getAsString());
            field.setType(jsonElement.getAsJsonObject().get("fields").getAsJsonArray().get(i).getAsJsonObject().get("type").getAsString());
            fields.add(field);
        }
    }
}
