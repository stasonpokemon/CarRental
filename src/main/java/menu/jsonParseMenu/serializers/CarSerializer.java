package menu.jsonParseMenu.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pojo.Car;

import java.lang.reflect.Type;

public class CarSerializer implements JsonSerializer<Car> {
    @Override
    public JsonElement serialize(Car car, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("name", car.getName());
        result.addProperty("state", car.getState());
        return result;

    }
}
