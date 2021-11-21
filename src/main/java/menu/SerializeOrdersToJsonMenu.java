package menu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.OrderDaoImpl;
import menu.serializers.CarSerializer;
import menu.serializers.ClientSerializer;
import menu.serializers.OrderSerializer;
import menu.serializers.RefundSerializer;
import pojo.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class SerializeOrdersToJsonMenu {
    public static final String ENTER_PATH = "Введите путь к файлу .json:";
    public static final String FAILED_TO_LOAD = "Не удалось загрузить инормацию в json файл...";
    public static final String LOAD_IS_COMPLETE = "Загрузка завершена...";

    private static SerializeOrdersToJsonMenu menu;
    private final Scanner scanner = new Scanner(System.in);

    public static SerializeOrdersToJsonMenu getMenu() {
        if (menu == null) {
            menu = new SerializeOrdersToJsonMenu();
        }
        return menu;
    }

    public void exportToJson() {
        System.out.println(ENTER_PATH);
        String path = scanner.next();
        try (Writer writer = new FileWriter(path)) {
            //            Создаём объект заказов и передаём в него заказы из бд
            OrdersForJson orders = OrdersForJson.getOrdersForJson(OrderDaoImpl.getOrderDao().readAll());

            Gson gson = new GsonBuilder()
//            .registerTypeAdapter(OrdersForJson.class, new OrdersForJsonSerializer())
                    .registerTypeAdapter(Order.class, new OrderSerializer())
                    .registerTypeAdapter(Car.class, new CarSerializer())
                    .registerTypeAdapter(Client.class, new ClientSerializer())
                    .registerTypeAdapter(Refund.class, new RefundSerializer())
                    .setPrettyPrinting()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX")
                    .create();
            gson.toJson(orders, writer);
            System.out.println(LOAD_IS_COMPLETE);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(FAILED_TO_LOAD);
        }
    }
}
