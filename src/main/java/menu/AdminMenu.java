package menu;

import menu.jsonParseMenu.DeserializeOrdersFromJsonMenu;
import menu.jsonParseMenu.SerializeClientsToJsonMenu;
import menu.jsonParseMenu.SerializeOrdersToJsonMenu;
import utils.JDBCConnector;
import utils.LanguagePropertyLoader;
import utils.NumberValidUtil;

import java.sql.SQLException;

public class AdminMenu implements Menu {

    private static int operationNumber;

    private static AdminMenu adminMenu;

    private AdminMenu() {
    }

    public static AdminMenu getInstance() {
        if (adminMenu == null) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }

    @Override
    public void getMenu() {
        Menu menu = null;
        boolean exit = false;
        do {
            operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("AM_MAIN_MENU"));
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
