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

/**
 * Класс меню, который позволяет реализовать регистрацию возврата автомобиля, реализующий интерфейс {@link Menu}, со свойствами <b>operationNumber</b> и <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class RefundRegistrationMenu implements Menu {

    /**
     * Поле сканера для чтения строк из консоли
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Поле номера выбора операции
     */
    private int operationNumber;

    /**
     * Статическое поле класса меню {@link RefundRegistrationMenu} для реализации Singleton
     */
    private static RefundRegistrationMenu instance;

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    private RefundRegistrationMenu() {
    }

    /**
     * Статическая функция получения значения поля {@link RefundRegistrationMenu#instance}
     *
     * @return возвращает экземпляр класса {@link RefundRegistrationMenu}
     */
    public static RefundRegistrationMenu getInstance() {
        if (instance == null) {
            instance = new RefundRegistrationMenu();
        }
        return instance;
    }

    /**
     * Функция вызова меню, которая позволяет реализовать регистрацию возврата автомобиля
     */
    @Override
    public void getMenu() {
        try {
            boolean exit = false;
            if (OrderService.getInstance().findApprovedOrdersWithoutRefund().size() == 0) {
                do {
                    operationNumber = NumberValidUtil.getInstance().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("RRM_NO_ORDER"));
                    if (operationNumber == 1) {
                        exit = true;
                    } else {
                        System.out.println(LanguagePropertyLoader.getProperty("RRM_NO_OPERATION"));
                    }
                } while (!exit);
            } else {
                do {
                    final List<Order> approvedOrdersWithoutRefund = OrderService.getInstance().findApprovedOrdersWithoutRefund();
                    for (Order order : approvedOrdersWithoutRefund) {
                        System.out.println(LanguagePropertyLoader.getProperty("RRM_ORDER_NUMBER") + " - " + order.getId() + ": " + order);
                    }
                    operationNumber = NumberValidUtil.getInstance().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("RRM_REFUND_MENU"));
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

    /**
     * Метод регистрации
     *
     * @throws NoConnectionJDBCException
     * @see NoConnectionJDBCException
     */
    private void registration() throws NoConnectionJDBCException {
        Order order = new Order();
        Refund refund;
        boolean exit = false;
        boolean orderNumberValid = false;
        do {
            int orderNumber = 0;
            orderNumber = NumberValidUtil.getInstance().intNumberValid(orderNumber, LanguagePropertyLoader.getProperty("RRM_ENTER_ORDER_NUMBER"));
            for (Order orderWithoutRefund : OrderService.getInstance().findApprovedOrdersWithoutRefund()) {
                if (orderWithoutRefund.getId().equals(orderNumber)) {
                    order = orderWithoutRefund;
                    orderNumberValid = true;
                    break;
                }
            }
            if (orderNumberValid) {
                System.out.println(LanguagePropertyLoader.getProperty("RRM_CAR_STATE") + " - " + order.getCar());
                operationNumber = NumberValidUtil.getInstance().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("RRM_CAR_STATE_MENU"));
                switch (operationNumber) {
                    case 1:
                        refund = new Refund();
                        refund.setState("Without Damage");
                        RefundService.getInstance().addNewRefund(refund);
                        order.setRefund(refund);
                        OrderService.getInstance().update(order);
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
                        price = NumberValidUtil.getInstance().doubleNumberValid(price, LanguagePropertyLoader.getProperty("RRM_ENTER_REFUND_PRICE"));
                        refund.setPrice(price);
                        RefundService.getInstance().addNewRefund(refund);
                        order.setRefund(refund);
                        OrderService.getInstance().update(order);
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
