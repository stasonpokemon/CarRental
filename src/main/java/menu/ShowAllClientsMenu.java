package menu;

import exceptions.NoConnectionJDBCException;
import pojo.Client;
import services.ClientService;
import utils.LanguagePropertyLoader;
import utils.NumberValidUtil;

import java.util.List;

/**
 * Класс меню, который выводит в консоль список всех клиентов, реализующий интерфейс {@link Menu}, со свойствами <b>operationNumber</b> и <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class ShowAllClientsMenu implements Menu {

    /**
     * Поле номера выбора операции
     */
    private int operationNumber;

    /**
     * Статическое поле класса меню {@link ShowAllClientsMenu} для реализации Singleton
     */
    private static ShowAllClientsMenu instance;

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    private ShowAllClientsMenu() {
    }

    /**
     * Статическая функция получения значения поля {@link ShowAllClientsMenu#instance}
     *
     * @return возвращает экземпляр класса {@link ShowAllClientsMenu}
     */
    public static ShowAllClientsMenu getInstance() {
        if (instance == null) {
            instance = new ShowAllClientsMenu();
        }
        return instance;
    }

    /**
     * Функция вызова меню, которое выводит в консоль список всех клиентов
     */
    @Override
    public void getMenu() {
        try {
            boolean exit = false;
            System.out.println(LanguagePropertyLoader.getProperty("SACM_LIST_OF_CLIENTS"));
            final List<Client> allClients = ClientService.getInstance().getAllClients();
            if (allClients.size() > 0) {
                allClients.forEach(System.out::println);
            } else {
                System.out.println(LanguagePropertyLoader.getProperty("SACM_NO_CLIENTS"));
            }
            do {
                operationNumber = NumberValidUtil.getInstance().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("SACM_GO_BACK"));
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
