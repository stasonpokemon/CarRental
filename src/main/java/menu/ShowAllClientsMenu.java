package menu;

import exceptions.NoConnectionJDBCException;
import pojo.Client;
import services.ClientService;
import utils.LanguagePropertyLoader;
import utils.NumberValidUtil;

import java.util.List;

public class ShowAllClientsMenu implements Menu {

    private static int operationNumber;
    private static ShowAllClientsMenu instance;

    private ShowAllClientsMenu() {
    }

    public static ShowAllClientsMenu getInstance() {
        if (instance == null) {
            instance = new ShowAllClientsMenu();
        }
        return instance;
    }

    @Override
    public void getMenu() {
        try {
            boolean exit = false;
            System.out.println(LanguagePropertyLoader.getProperty("SACM_LIST_OF_CLIENTS"));
            final List<Client> allClients = ClientService.getService().getAllClients();
            if (allClients.size() > 0) {
                allClients.forEach(System.out::println);
            } else {
                System.out.println(LanguagePropertyLoader.getProperty("SACM_NO_CLIENTS"));
            }
            do {
                operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("SACM_GO_BACK"));
                if (operationNumber == 1) {
                    exit = true;
                } else {
                    System.out.println(LanguagePropertyLoader.getProperty("SACM_NO_OPERATION"));
                }
            } while (!exit);
        } catch (NoConnectionJDBCException e) {
            e.printStackTrace();
        }
    }
}
