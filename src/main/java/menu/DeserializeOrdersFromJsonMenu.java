package menu;

import com.google.gson.*;
import menu.deserializers.CarDeserializer;
import menu.deserializers.ClientDeserializer;
import menu.deserializers.OrderDeserializer;
import menu.deserializers.RefundDeserializer;
import pojo.Car;
import pojo.Client;
import pojo.Order;
import pojo.Refund;
import services.OrderService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;


public class DeserializeOrdersFromJsonMenu {
    public static final String ENTER_PATH = "Введите путь к файлу .json:";
    public static final String FAILED_TO_LOAD = "Не удается найти указанный json файл - ";
    public static final String LOAD_IS_COMPLETE = "Загрузка завершена...";


    private static DeserializeOrdersFromJsonMenu menu;
    private final Scanner scanner = new Scanner(System.in);

    public static DeserializeOrdersFromJsonMenu getMenu() {
        if (menu == null) {
            menu = new DeserializeOrdersFromJsonMenu();
        }
        return menu;
    }

    //    Парсинг JSON из ТЗ
    public void parseJson() {
        System.out.println(ENTER_PATH);
        String path = scanner.next();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX")
                .registerTypeAdapter(Order.class, new OrderDeserializer())
                .registerTypeAdapter(Car.class, new CarDeserializer())
                .registerTypeAdapter(Client.class, new ClientDeserializer())
                .registerTypeAdapter(Refund.class, new RefundDeserializer())
                .create();
        try (Reader reader = new FileReader(path)) {
            final JsonElement jsonElement = JsonParser.parseReader(reader);
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            final JsonElement jsonElement1 = asJsonObject.get("orders");
            final JsonArray asJsonArray = jsonElement1.getAsJsonArray();
            for (JsonElement element : asJsonArray) {
                final JsonObject asJsonObject1 = element.getAsJsonObject();
                final Order order = gson.fromJson(asJsonObject1, Order.class);

//                Проверка на уникальность заказа
                boolean orderUniqValid = true;
                for (Order orderByDb : OrderService.getOrderService().findAllOrders()) {
                    if (order.getPrice() == orderByDb.getPrice()
                            && order.getState().equals(orderByDb.getState())
                            && order.getDate().equals(orderByDb.getDate())
                            && order.getTime() == orderByDb.getTime()
                            && order.getCar().getName().equals(orderByDb.getCar().getName())
                            && order.getClient().getName().equals(orderByDb.getClient().getName())) {
                        orderUniqValid = false;
                    }
                }
//                Если заказ не повторяется в бд, то происходит добавление
                if (orderUniqValid) {
                    OrderService.getOrderService().addOrder(order);
                }
            }
            System.out.println(LOAD_IS_COMPLETE);
        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + e);
            System.out.println(FAILED_TO_LOAD + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}