package menu.deserializers;

import com.google.gson.*;
import pojo.Refund;

import java.lang.reflect.Type;

public class RefundDeserializer implements JsonDeserializer<Refund> {
    @Override
    public Refund deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        Refund refund = new Refund();
        refund.setState(asJsonObject.get("state").getAsString());
        if (asJsonObject.get("detail") != null) {
            refund.setDetail(asJsonObject.get("detail").getAsString());
        }
        refund.setPrice(asJsonObject.get("price").getAsDouble());
        return refund;
    }
}
