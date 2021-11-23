package menu;

import exceptions.NoConnectionJDBCException;
import pojo.Order;
import services.OrderService;
import utils.NumberValidUtil;

import java.util.List;

public class MenuOfAllOrders extends Menu {

    private static final String LIST_OF_ORDERS = "Список всех заказов:";
    private static final String NO_ORDERS = "Нет заказов...";
    private static final String GO_BACK = "1. Назад";
    private static final String NO_OPERATION = "Не существует введённой вами операции, попробуйте ещё раз...";


    private static int operationNumber;
    private static MenuOfAllOrders menu;

    private MenuOfAllOrders() {
    }

    public static MenuOfAllOrders getInstance() {
        if (menu == null) {
            menu = new MenuOfAllOrders();
        }
        return menu;
    }

    @Override
    public void getMenu() {
        try {
            boolean exit = false;
            System.out.println(LIST_OF_ORDERS);
            final List<Order> allOrders = OrderService.getOrderService().findAllOrders();
            if (allOrders.size() > 0) {
                allOrders.forEach(System.out::println);
            } else {
                System.out.println(NO_ORDERS);
            }
            do {
                operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, GO_BACK);
                if (operationNumber == 1) {
                    exit = true;
                } else {
                    System.out.println(NO_OPERATION);
                }
            } while (!exit);
        } catch (NoConnectionJDBCException e) {
            e.printStackTrace();
        }
    }
}
