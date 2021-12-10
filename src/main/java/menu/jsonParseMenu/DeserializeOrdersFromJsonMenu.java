package menu.jsonParseMenu;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import exceptions.NoConnectionJDBCException;
import menu.Menu;
import menu.jsonParseMenu.deserializers.CarDeserializer;
import menu.jsonParseMenu.deserializers.ClientDeserializer;
import menu.jsonParseMenu.deserializers.OrderDeserializer;
import menu.jsonParseMenu.deserializers.RefundDeserializer;
import pojo.Car;
import pojo.Client;
import pojo.Order;
import pojo.Refund;
import services.OrderService;
import utils.LanguagePropertyLoader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;


public class DeserializeOrdersFromJsonMenu implements Menu {


    private static DeserializeOrdersFromJsonMenu menu;
    private final Scanner scanner = new Scanner(System.in);

    private DeserializeOrdersFromJsonMenu() {
    }

    public static DeserializeOrdersFromJsonMenu getInstance() {
        if (menu == null) {
            menu = new DeserializeOrdersFromJsonMenu();
        }
        return menu;
    }

    @Override
    public void getMenu() {
        try {
            System.out.println(LanguagePropertyLoader.getProperty("DOFJM_ENTER_PATH"));
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
                List<Order> ordersFromJson = gson.fromJson(asJsonArray, new TypeToken<List<Order>>() {
                }.getType());
//                Проверка на уникальность заказа
                final List<Order> ordersFromDb = OrderService.getOrderService().findAllOrders();
                List<Order> toDB = ordersFromJson.stream().filter(s1 -> ordersFromDb.stream()
                        .noneMatch(s2 -> isSame(s1, s2))).collect(toList());
                toDB.forEach(order -> {
                    try {
                        OrderService.getOrderService().addOrder(order);
                    } catch (NoConnectionJDBCException e) {
                        e.printStackTrace();
                    }
                });
                System.out.println(LanguagePropertyLoader.getProperty("DOFJM_LOAD_IS_COMPLETE"));
            } catch (FileNotFoundException e) {
                System.out.println("Exception: " + e);
                System.out.println(LanguagePropertyLoader.getProperty("DOFJM_FAILED_TO_LOAD") + path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NoConnectionJDBCException e) {
            e.printStackTrace();
        }
    }

    private boolean isSame(Order o1, Order o2) {
        return o1.getPrice() == o2.getPrice()
                && o1.getState().equals(o2.getState())
                && o1.getDate().equals(o2.getDate())
                && o1.getTime() == o2.getTime()
                && o1.getCar().getName().equals(o2.getCar().getName())
                && o1.getClient().getName().equals(o2.getClient().getName());
    }
}