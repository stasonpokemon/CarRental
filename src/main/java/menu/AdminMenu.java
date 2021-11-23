package menu;

import menu.jsonParseMenu.DeserializeOrdersFromJsonMenu;
import menu.jsonParseMenu.SerializeOrdersToJsonMenu;
import utils.NumberValidUtil;

public class AdminMenu extends Menu{

    private static final String MAIN_MENU = "Меню администратора:\n" +
            "1. Новый заказ\n" +
            "2. Регистрация возврата заказа\n" +
            "3. Список всех заказов\n" +
            "4. Экспортировать(Сериализация) все заказы в JSON файл\n" +
            "5. Имортировать(Дисериализация) данные о заказах в бд\n" +
            "6. Выход";
    private static final String NO_OPERATION = "Не существует введённой вами операции, попробуйте ещё раз...";
    private static final String EXIT = "Выход...";


    private static int operationNumber;

    private static AdminMenu adminMenu;

    public static AdminMenu getInstance() {
        if (adminMenu == null) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }

    public void menu() {

    }

    @Override
    public void getMenu() {
        Menu menu = null;
        boolean exit = false;
        do {
            operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, MAIN_MENU);
            switch (operationNumber) {
                case 1:
                    menu = OrderCreationMenu.getInstance();
                    break;
                case 2:
                    menu = RefundRegistrationMenu.getInstance();
                    break;
                case 3:
                    menu = MenuOfAllOrders.getInstance();
                    break;
                case 4:
                    menu = SerializeOrdersToJsonMenu.getInstance();
                    break;
                case 5:
                    menu = DeserializeOrdersFromJsonMenu.getInstance();
                    break;
                case 6:
                    exit = true;
                    System.out.println(EXIT);
                    break;
                default:
                    System.out.println(NO_OPERATION);
                    break;
            }
//            Механизм позднего(динамического) связывания
            menu.getMenu();

        } while (!exit);
    }
}
