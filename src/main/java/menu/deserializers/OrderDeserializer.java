package menu.deserializers;

import com.google.gson.*;
import pojo.Car;
import pojo.Client;
import pojo.Order;
import pojo.Refund;
import utils.ParseOrderTime;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderDeserializer implements JsonDeserializer<Order> {
    @Override
    public Order deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        Order order = new Order();
        final String priceString = asJsonObject.get("price").getAsString();
        final String newPriceString = priceString.replace("$", "");
        order.setPrice(Double.parseDouble(newPriceString));
        order.setState(asJsonObject.get("state").getAsString());
        String dateString = asJsonObject.get("date").getAsString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
        try {
            order.setDate(new Timestamp(format.parse(dateString).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setTime(ParseOrderTime.parseTimeFromJson(asJsonObject.get("time").getAsString()));
        order.setCar(jsonDeserializationContext.deserialize(asJsonObject.get("car"), Car.class));
        order.setClient(jsonDeserializationContext.deserialize(asJsonObject.get("client"), Client.class));
        order.setRefund(jsonDeserializationContext.deserialize(asJsonObject.get("returned"), Refund.class));

        return order;
    }
}
