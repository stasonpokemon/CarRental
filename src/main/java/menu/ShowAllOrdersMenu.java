package menu;

import exceptions.NoConnectionJDBCException;
import pojo.Order;
import services.OrderService;
import utils.LanguagePropertyLoader;
import utils.NumberValidUtil;

import java.util.List;

/**
 * Класс меню, который выводит в консоль список всех заказов, реализующий интерфейс {@link Menu}, со свойствами <b>operationNumber</b> и <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class ShowAllOrdersMenu implements Menu {

    /**
     * Поле номера выбора операции
     */
    private int operationNumber;

    /**
     * Статическое поле класса меню {@link ShowAllOrdersMenu} для реализации Singleton
     */
    private static ShowAllOrdersMenu instance;

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    private ShowAllOrdersMenu() {
    }

    /**
     * Статическая функция получения значения поля {@link ShowAllOrdersMenu#instance}
     *
     * @return возвращает экземпляр класса {@link ShowAllOrdersMenu}
     */
    public static ShowAllOrdersMenu getInstance() {
        if (instance == null) {
            instance = new ShowAllOrdersMenu();
        }
        return instance;
    }

    /**
     * Функция вызова меню, которое выводит в консоль список всех заказов
     */
    @Override
    public void getMenu() {
        try {
            boolean exit = false;
            System.out.println(LanguagePropertyLoader.getProperty("SAOM_LIST_OF_ORDERS"));
            final List<Order> allOrders = OrderService.getInstance().findAllOrders();
            if (allOrders.size() > 0) {
                allOrders.forEach(System.out::println);
            } else {
                System.out.println(LanguagePropertyLoader.getProperty("SAOM_NO_ORDERS"));
            }
            do {
                operationNumber = NumberValidUtil.getInstance().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("SAOM_GO_BACK"));
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
