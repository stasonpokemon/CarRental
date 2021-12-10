import menu.AdminMenu;
import menu.Menu;
import utils.LanguagePropertyLoader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        final Menu adminMenu = AdminMenu.getInstance();
        adminMenu.getMenu();


//        LanguagePropertyLoader languagePropertyLoader = new LanguagePropertyLoader();
//        languagePropertyLoader.selectEnglishLanguageProperty();
//        final String saom_list_of_orders = languagePropertyLoader.getProperty("SAOM_LIST_OF_ORDERS");
//        System.out.println(saom_list_of_orders);

    }
}
