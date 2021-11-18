import com.google.gson.*;
import dao.OrderDaoImpl;
import menu.AdminMenu;
import pojo.Entity;
import pojo.Order;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final AdminMenu adminMenu = AdminMenu.getAdminMenu();
        adminMenu.menu();

//        Запись в JSON
        String path = "orders2.json";
        writeToJSON(path);


//        Чтение из JSON
        String path2 = "orders.json";
        final List<Order> orders = readFromJson(path2, Order.class);
        orders.forEach(System.out::println);


    }

    private  static <T extends Entity> List<Order> readFromJson(String path, Class<T> orderClass) {
        List<Order> orders = new ArrayList<>();
        Gson gson = new Gson();
        try (Reader reader = new FileReader(path)) {
            final JsonElement jsonElement = JsonParser.parseReader(reader);
            final JsonArray asJsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : asJsonArray) {
                orders.add(gson.fromJson(element, (Type) orderClass));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private static void writeToJSON(String path) {
        List<Order> orders = OrderDaoImpl.getOrderDaoImpl().readAll();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(orders, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
