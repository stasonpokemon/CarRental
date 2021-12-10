package menu.jsonParseMenu;

import com.google.gson.GsonBuilder;
import exceptions.NoConnectionJDBCException;
import menu.Menu;
import menu.jsonParseMenu.serializers.CarSerializer;
import menu.jsonParseMenu.serializers.ClientSerializer;
import menu.jsonParseMenu.serializers.OrderSerializer;
import menu.jsonParseMenu.serializers.RefundSerializer;
import pojo.*;
import services.OrderService;
import utils.LanguagePropertyLoader;

import java.util.List;
import java.util.Scanner;

public class SerializeOrdersToJsonMenu extends SerializeToJson implements Menu {
    private final Scanner scanner = new Scanner(System.in);

    private static SerializeOrdersToJsonMenu menu;

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
            System.out.println(LanguagePropertyLoader.getProperty("SOTJM_ENTER_PATH"));
            String path = scanner.next();
            gson = new GsonBuilder()
                    .registerTypeAdapter(Order.class, new OrderSerializer())
                    .registerTypeAdapter(Car.class, new CarSerializer())
                    .registerTypeAdapter(Client.class, new ClientSerializer())
                    .registerTypeAdapter(Refund.class, new RefundSerializer())
                    .setPrettyPrinting()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX")
                    .create();
            propName = "orders";
            final List<Order> allOrders = OrderService.getOrderService().findAllOrders();
            serialize(allOrders, path);
        } catch (NoConnectionJDBCException e) {
            e.printStackTrace();
        }
    }
}
