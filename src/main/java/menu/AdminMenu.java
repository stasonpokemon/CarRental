package menu;

import menu.jsonParseMenu.DeserializeOrdersFromJsonMenu;
import menu.jsonParseMenu.SerializeClientsToJsonMenu;
import menu.jsonParseMenu.SerializeOrdersToJsonMenu;
import utils.JDBCConnector;
import utils.LanguagePropertyLoader;
import utils.NumberValidUtil;

import java.sql.SQLException;

/**
 * Класс главного меню приложения, реализующий интерфейс {@link Menu}, со свойствами <b>operationNumber</b> и <b>instance</b>.
 *
 * @version 1.1
 * @autor Stanislav Trebnikov
 */
public class AdminMenu implements Menu {

    /**
     * Поле номера выбора операции
     */
    private int operationNumber;

    /**
     * Статическое поле класса меню {@link AdminMenu} для реализации Singleton
     */
    private static AdminMenu instance;

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    public AdminMenu() {
    }

    /**
     * Статическая функция получения значения поля {@link AdminMenu#instance}
     *
     * @return возвращает экземпляр класса {@link AdminMenu}
     */
    public static AdminMenu getInstance() {
        if (instance == null) {
            instance = new AdminMenu();
        }
        return instance;
    }

    /**
     * Функция вызова главного меню
     */
    @Override
    public void getMenu() {
        Menu menu = null;
        boolean exit = false;
        do {
            operationNumber = NumberValidUtil.getInstance().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("AM_MAIN_MENU"));
            switch (operationNumber) {
                case 1:
                    menu = OrderCreationMenu.getInstance();
                    break;
                case 2:
                    menu = RefundRegistrationMenu.getInstance();
                    break;
                case 3:
                    menu = ShowAllOrdersMenu.getInstance();
                    break;
                case 4:
                    menu = ShowAllClientsMenu.getInstance();
                    break;
                case 5:
                    menu = SerializeOrdersToJsonMenu.getInstance();
                    break;
                case 6:
                    menu = SerializeClientsToJsonMenu.getInstance();
                    break;
                case 7:
                    menu = DeserializeOrdersFromJsonMenu.getInstance();
                    break;
                case 8:
                    menu = SelectLanguageMenu.getInstance();
                    break;
                case 9:
                    exit = true;
                    menu = null;
//                    Закрываем connection
                    try {
                        JDBCConnector.getInstance().connectionClose();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println(LanguagePropertyLoader.getProperty("AM_EXIT"));
                    System.exit(0);
                default:
                    System.out.println(LanguagePropertyLoader.getProperty("AM_NO_OPERATION"));
                    break;
            }
//            Механизм позднего(динамического) связывания
            if (menu != null) {
                menu.getMenu();
            }
        } while (!exit);
    }
}
