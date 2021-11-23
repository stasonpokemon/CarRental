package menu.jsonParseMenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.NoConnectionJDBCException;
import menu.Menu;
import menu.jsonParseMenu.serializers.CarSerializer;
import menu.jsonParseMenu.serializers.ClientSerializer;
import menu.jsonParseMenu.serializers.OrderSerializer;
import menu.jsonParseMenu.serializers.RefundSerializer;
import pojo.*;
import services.OrderService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class SerializeOrdersToJsonMenu extends Menu {
    public static final String ENTER_PATH = "Введите путь к файлу .json:";
    public static final String FAILED_TO_LOAD = "Не удалось загрузить инормацию в json файл...";
    public static final String LOAD_IS_COMPLETE = "Загрузка завершена...";

    private static SerializeOrdersToJsonMenu menu;
    private final Scanner scanner = new Scanner(System.in);

    private SerializeOrdersToJsonMenu() {
    }

    public static SerializeOrdersToJsonMenu getInstance() {
        if (menu == null) {
            menu = new SerializeOrdersToJsonMenu();
        }
        return menu;
    }

    @Override
    public void getMenu() {
        try {
            System.out.println(ENTER_PATH);
            String path = scanner.next();
            //            Создаём объект заказов и передаём в него заказы из бд
            OrdersForJson orders = OrdersForJson.getOrdersForJson();
            orders.createOrdersForJson(OrderService.getOrderService().findAllOrders());
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Order.class, new OrderSerializer())
                    .registerTypeAdapter(Car.class, new CarSerializer())
                    .registerTypeAdapter(Client.class, new ClientSerializer())
                    .registerTypeAdapter(Refund.class, new RefundSerializer())
                    .setPrettyPrinting()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX")
                    .create();
            try (Writer writer = new FileWriter(path)) {
                gson.toJson(orders, writer);
                System.out.println(LOAD_IS_COMPLETE);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(FAILED_TO_LOAD);
            }
        } catch (NoConnectionJDBCException e) {
            e.printStackTrace();
        }
    }
}
