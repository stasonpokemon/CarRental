import menu.AdminMenu;
import menu.Menu;



public class Main {
    public static void main(String[] args) {

        final Menu adminMenu = AdminMenu.getInstance();
        adminMenu.getMenu();

    }
}
