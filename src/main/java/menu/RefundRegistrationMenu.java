package menu;

import exceptions.NoConnectionJDBCException;
import pojo.Order;
import pojo.Refund;
import services.OrderService;
import services.RefundService;
import utils.LanguagePropertyLoader;
import utils.NumberValidUtil;

import java.util.List;
import java.util.Scanner;

public class RefundRegistrationMenu implements Menu {

    private final Scanner scanner = new Scanner(System.in);

    private static int operationNumber;

    private static RefundRegistrationMenu menu;

    private RefundRegistrationMenu() {
    }

    public static RefundRegistrationMenu getInstance() {
        if (menu == null) {
            menu = new RefundRegistrationMenu();
        }
        return menu;
    }


    @Override
    public void getMenu() {
        try {
            boolean exit = false;
            if (OrderService.getOrderService().findApprovedOrdersWithoutRefund().size() == 0) {
                do {
                    operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("RRM_NO_ORDER"));
                    if (operationNumber == 1) {
                        exit = true;
                    } else {
                        System.out.println(LanguagePropertyLoader.getProperty("RRM_NO_OPERATION"));
                    }
                } while (!exit);
            } else {
                do {
                    final List<Order> approvedOrdersWithoutRefund = OrderService.getOrderService().findApprovedOrdersWithoutRefund();
                    for (Order order : approvedOrdersWithoutRefund) {
                        System.out.println(LanguagePropertyLoader.getProperty("RRM_ORDER_NUMBER") + " - " + order.getId() + ": " + order);
                    }
                    operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("RRM_REFUND_MENU"));
                    switch (operationNumber) {
                        case 1:
                            registration();
                            exit = true;
                            break;
                        case 2:
                            exit = true;
                            System.out.println(LanguagePropertyLoader.getProperty("RRM_EXIT"));
                            break;
                        default:
                            System.out.println(LanguagePropertyLoader.getProperty("RRM_NO_OPERATION"));
                            break;
                    }
                } while (!exit);
            }
        } catch (NoConnectionJDBCException e) {
            e.printStackTrace();
        }
    }

    private void registration() throws NoConnectionJDBCException {
        Order order = new Order();
        Refund refund;
        boolean exit = false;
        boolean orderNumberValid = false;
        do {
            int orderNumber = 0;
            orderNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(orderNumber, LanguagePropertyLoader.getProperty("RRM_ENTER_ORDER_NUMBER"));
            for (Order orderWithoutRefund : OrderService.getOrderService().findApprovedOrdersWithoutRefund()) {
                if (orderWithoutRefund.getId().equals(orderNumber)) {
                    order = orderWithoutRefund;
                    orderNumberValid = true;
                    break;
                }
            }
            if (orderNumberValid) {
                System.out.println(LanguagePropertyLoader.getProperty("RRM_CAR_STATE") + " - " + order.getCar());
                operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("RRM_CAR_STATE_MENU"));
                switch (operationNumber) {
                    case 1:
                        refund = new Refund();
                        refund.setState("Without Damage");
                        RefundService.getInstance().addNewRefund(refund);
                        order.setRefund(refund);
                        OrderService.getOrderService().update(order);
                        System.out.println(LanguagePropertyLoader.getProperty("RRM_REFUND_REGISTERED"));
                        exit = true;
                        break;
                    case 2:
                        refund = new Refund();
                        refund.setState("Broken");
                        System.out.println(LanguagePropertyLoader.getProperty("RRM_ENTER_REFUND_DETAIL"));
                        String detail = scanner.nextLine();
                        refund.setDetail(detail);
                        double price = 0;
                        price = NumberValidUtil.getOperationNumberUtil().doubleNumberValid(price, LanguagePropertyLoader.getProperty("RRM_ENTER_REFUND_PRICE"));
                        refund.setPrice(price);
                        RefundService.getInstance().addNewRefund(refund);
                        order.setRefund(refund);
                        OrderService.getOrderService().update(order);
                        System.out.println(LanguagePropertyLoader.getProperty("RRM_REFUND_REGISTERED"));
                        exit = true;
                        break;
                    default:
                        System.out.println(LanguagePropertyLoader.getProperty("RRM_NO_OPERATION"));
                        break;
                }
            } else {
                System.out.println(LanguagePropertyLoader.getProperty("RRM_NO_ORDER_BY_NUMBER"));
                exit = true;
            }
        } while (!exit);
    }

}
