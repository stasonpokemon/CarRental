//package menu.serializers;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonSerializationContext;
//import com.google.gson.JsonSerializer;
//import pojo.Order;
//import pojo.OrdersForJson;
//
//import java.lang.reflect.Type;
//
//public class OrdersForJsonSerializer implements JsonSerializer<OrdersForJson> {
//    @Override
//    public JsonElement serialize(OrdersForJson ordersForJson, Type type, JsonSerializationContext jsonSerializationContext) {
//        JsonObject result = new JsonObject();
//        for (Order order : ordersForJson.getOrders()) {
//            result.add(order.getClient().getName(),jsonSerializationContext.serialize(order));
//        }
//
//        return result;
//    }
//}
