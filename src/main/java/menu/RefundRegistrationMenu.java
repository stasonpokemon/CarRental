package menu;

import pojo.Order;
import pojo.Refund;
import services.OrderService;
import services.RefundService;
import utils.NumberValidUtil;

import java.util.List;
import java.util.Scanner;

public class RefundRegistrationMenu {

    private static final String ORDER_NUMBER = "Номер заказа";
    private static final String REFUND_MENU = "1. Выбрать заказ для регистрации возврата:\n" +
            "2. Назад";
    private static final String ENTER_ORDER_NUMBER = "Введите номер заказа...";
    private static final String NO_ORDER = "Нет заказов требующих возврат...\n" +
            "1. Назад";
    private static final String NO_ORDER_BY_NUMBER = "Нет заказа с указанным номером...";
    private static final String NO_OPERATION = "Не существует введённой вами операции, попробуйте ещё раз...";
    private static final String CAR_STATE = "Укажите статус автомобиля";
    private static final String CAR_STATE_MENU = "1. Без повреждений\n" +
            "2. Имеются плвреждения";
    private static final String REFUND_REGISTERED = "Возврат зарегистрирован...";
    private static final String ENTER_REFUND_DETAIL = "Опишите повреждения...";
    private static final String ENTER_REFUND_PRICE = "Укажите цену за повреждения...";
    private static final String EXIT = "Выход...";
    private final Scanner scanner = new Scanner(System.in);

    private static int operationNumber;

    private static RefundRegistrationMenu menu;

    public static RefundRegistrationMenu getMenu() {
        if (menu == null) {
            menu = new RefundRegistrationMenu();
        }
        return menu;
    }

    public void refundRegistration() {
        boolean exit = false;
        if (OrderService.getOrderService().findApprovedOrdersWithoutRefund().size() == 0) {
            do {
                operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, NO_ORDER);
                if (operationNumber == 1) {
                    exit = true;
                } else {
                    System.out.println(NO_OPERATION);
                }

            } while (!exit);
        } else {
            do {
                final List<Order> approvedOrdersWithoutRefund = OrderService.getOrderService().findApprovedOrdersWithoutRefund();
                for (Order order : approvedOrdersWithoutRefund) {
                    System.out.println(ORDER_NUMBER + " - " + order.getId() + ": " + order);
                }

                operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, REFUND_MENU);
                switch (operationNumber) {
                    case 1:
                        registration();
                        break;
                    case 2:
                        exit = true;
                        System.out.println(EXIT);
                        break;
                    default:
                        System.out.println(NO_OPERATION);
                        break;
                }
            } while (!exit);
        }

    }

    private void registration() {
        Order order = new Order();
        Refund refund;
        boolean exit = false;
        boolean orderNumberValid = false;
        do {
            int orderNumber = 0;
            orderNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(orderNumber, ENTER_ORDER_NUMBER);
            for (Order orderWithoutRefund : OrderService.getOrderService().findApprovedOrdersWithoutRefund()) {
                if (orderWithoutRefund.getId().equals(orderNumber)) {
                    order = orderWithoutRefund;
                    orderNumberValid = true;
                    break;
                }
            }
            if (orderNumberValid) {
                System.out.println(CAR_STATE + " - " + order.getCar());
                operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, CAR_STATE_MENU);
                switch (operationNumber) {
                    case 1:
                        refund = new Refund();
                        refund.setState("Without Damage");
                        RefundService.getService().addNewRefund(refund);
                        order.setRefund(refund);
                        OrderService.getOrderService().update(order);
                        System.out.println(REFUND_REGISTERED);
                        exit = true;
                        break;
                    case 2:
                        refund = new Refund();
                        refund.setState("Broken");
                        System.out.println(ENTER_REFUND_DETAIL);
                        String detail = scanner.nextLine();
                        refund.setDetail(detail);
                        double price = 0;
                        price = NumberValidUtil.getOperationNumberUtil().doubleNumberValid(price, ENTER_REFUND_PRICE);
                        refund.setPrice(price);
                        RefundService.getService().addNewRefund(refund);
                        order.setRefund(refund);
                        OrderService.getOrderService().update(order);
                        System.out.println(REFUND_REGISTERED);
                        exit = true;
                        break;
                    default:
                        System.out.println(NO_OPERATION);
                        break;
                }
            } else {
                System.out.println(NO_ORDER_BY_NUMBER);
                exit = true;
            }
        } while (!exit);
    }
}
