package menu;

import services.OrderService;
import utils.NumberValidUtil;

public class MenuOfAllOrders {

    private static final String LIST_OF_ORDERS = "Список всех заказов:";
    private static final String GO_BACK = "1. Назад";
    private static final String NO_OPERATION = "Не существует введённой вами операции, попробуйте ещё раз...";


    private static int operationNumber;
    private static MenuOfAllOrders menu;

    public static MenuOfAllOrders getMenu() {
        if (menu == null) {
            menu = new MenuOfAllOrders();
        }
        return menu;
    }

    public void allOrders() {
        boolean exit = false;
        System.out.println(LIST_OF_ORDERS);
        OrderService.getOrderService().findAllOrders().forEach(System.out::println);
        do {
            operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, GO_BACK);
            if (operationNumber == 1) {

                exit = true;
            } else {
                System.out.println(NO_OPERATION);
            }
        } while (!exit);
    }
}
