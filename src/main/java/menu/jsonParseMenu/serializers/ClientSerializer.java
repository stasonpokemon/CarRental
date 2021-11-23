package menu.jsonParseMenu.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pojo.Client;

import java.lang.reflect.Type;

public class ClientSerializer implements JsonSerializer<Client> {
    @Override
    public JsonElement serialize(Client client, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("name", client.getName());
        result.addProperty("address", client.getAddress());
        return result;
    }
}
