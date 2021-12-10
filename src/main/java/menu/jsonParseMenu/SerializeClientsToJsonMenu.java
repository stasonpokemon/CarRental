package menu.jsonParseMenu;

import com.google.gson.GsonBuilder;
import exceptions.NoConnectionJDBCException;
import menu.Menu;
import pojo.Client;
import services.ClientService;
import utils.LanguagePropertyLoader;

import java.util.List;
import java.util.Scanner;

public class SerializeClientsToJsonMenu extends SerializeToJson implements Menu {
    private final Scanner scanner = new Scanner(System.in);
    private static SerializeClientsToJsonMenu instance;

    public static SerializeClientsToJsonMenu getInstance() {
        if (instance == null) {
            instance = new SerializeClientsToJsonMenu();
        }
        return instance;
    }

    private SerializeClientsToJsonMenu() {
    }

    @Override
    public void getMenu() {
        try {
            System.out.println(LanguagePropertyLoader.getProperty("SCLTJM_ENTER_PATH"));
            String path = scanner.next();
            gson = new GsonBuilder().setPrettyPrinting().create();
            propName = "clients";
            final List<Client> allClients = ClientService.getService().getAllClients();
            serialize(allClients, path);
        } catch (NoConnectionJDBCException e) {
            e.printStackTrace();
        }
    }
}
