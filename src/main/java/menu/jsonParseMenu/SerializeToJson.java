package menu.jsonParseMenu;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pojo.Entity;
import utils.LanguagePropertyLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public abstract class SerializeToJson {
    protected Gson gson;
    protected String propName;


    protected <T extends Entity> void serialize(List<T> data, String filePathName) {
        try (Writer writer = new FileWriter(filePathName)) {
            final JsonElement element = gson.toJsonTree(data);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(propName, element);
            gson.toJson(jsonObject, writer);
            System.out.println(LanguagePropertyLoader.getProperty("STJ_LOAD_IS_COMPLETE"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(LanguagePropertyLoader.getProperty("STJ_FAILED_TO_LOAD"));
        }
    }
}
