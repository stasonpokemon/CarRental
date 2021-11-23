package menu.jsonParseMenu.deserializers;

import com.google.gson.*;
import pojo.Client;

import java.lang.reflect.Type;

public class ClientDeserializer implements JsonDeserializer<Client> {
    @Override
    public Client deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        Client client = new Client();
        client.setName(asJsonObject.get("name").getAsString());
        client.setAddress(asJsonObject.get("address").getAsString());
        return client;
    }
}
