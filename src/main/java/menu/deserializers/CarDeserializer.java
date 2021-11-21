package menu.deserializers;

import com.google.gson.*;
import pojo.Car;

import java.lang.reflect.Type;

public class CarDeserializer implements JsonDeserializer<Car> {
    @Override
    public Car deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        Car car = new Car();
        car.setName(asJsonObject.get("name").getAsString());
        car.setState(asJsonObject.get("state").getAsString());
        return car;
    }
}
