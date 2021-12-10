package menu;

import exceptions.NoConnectionJDBCException;
import pojo.Order;
import services.OrderService;
import utils.LanguagePropertyLoader;
import utils.NumberValidUtil;

import java.awt.*;
import java.util.List;

public class ShowAllOrdersMenu implements Menu {

    private static int operationNumber;
    private static ShowAllOrdersMenu instance;

    private ShowAllOrdersMenu() {
    }

    public static ShowAllOrdersMenu getInstance() {
        if (instance == null) {
            instance = new ShowAllOrdersMenu();
        }
        return instance;
    }

    @Override
    public void getMenu() {
        try {
            boolean exit = false;
            System.out.println(LanguagePropertyLoader.getProperty("SAOM_LIST_OF_ORDERS"));
            final List<Order> allOrders = OrderService.getOrderService().findAllOrders();
            if (allOrders.size() > 0) {
                allOrders.forEach(System.out::println);
            } else {
                System.out.println(LanguagePropertyLoader.getProperty("SAOM_NO_ORDERS"));
            }
            do {
                operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("SAOM_GO_BACK"));
                if (operationNumber == 1) {
                    exit = true;
                } else {
                    System.out.println(LanguagePropertyLoader.getProperty("SAOM_NO_OPERATION"));
                }
            } while (!exit);
        } catch (NoConnectionJDBCException e) {
            e.printStackTrace();
        }
    }
}
