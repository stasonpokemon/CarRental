package menu.jsonParseMenu.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pojo.Refund;

import java.lang.reflect.Type;

public class RefundSerializer implements JsonSerializer<Refund> {
    @Override
    public JsonElement serialize(Refund refund, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("state", refund.getState());
        if (refund.getDetail() != null){
            result.addProperty("detail", refund.getDetail());
        }
        result.addProperty("price",refund.getPrice());
        return result;
    }
}
