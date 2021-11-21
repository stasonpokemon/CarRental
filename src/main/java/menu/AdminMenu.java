package menu;

import utils.NumberValidUtil;

public class AdminMenu {

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

    public static AdminMenu getAdminMenu() {
        if (adminMenu == null) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }

    public void menu() {
        boolean exit = false;
        do {
            operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, MAIN_MENU);
            switch (operationNumber) {
                case 1:
                    OrderCreationMenu.getMenu().creatingAnOrder();
                    break;
                case 2:
                    RefundRegistrationMenu.getMenu().refundRegistration();
                    break;
                case 3:
                    MenuOfAllOrders.getMenu().allOrders();
                    break;
                case 4:
                    SerializeOrdersToJsonMenu.getMenu().exportToJson();
                    break;
                case 5:
                    DeserializeOrdersFromJsonMenu.getMenu().parseJson();
                    break;
                case 6:
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
