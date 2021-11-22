package menu.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pojo.Order;
import utils.ParseOrderTime;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class OrderSerializer implements JsonSerializer<Order> {
    @Override
    public JsonElement serialize(Order order, Type type, JsonSerializationContext jsonSerializationContext) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
        JsonObject result = new JsonObject();
        double price = order.getPrice();
        String serializePrice = "$"+price;
        result.addProperty("price",serializePrice);
        result.addProperty("state",order.getState());
        result.addProperty("date", format.format(order.getDate()));
        result.addProperty("time", ParseOrderTime.parseTimeToJson(order.getTime()));
        result.add("car", jsonSerializationContext.serialize(order.getCar()));
        result.add("client", jsonSerializationContext.serialize(order.getClient()));
        result.add("returned", jsonSerializationContext.serialize(order.getRefund()));

        return result;
    }
}
