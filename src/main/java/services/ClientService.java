package services;

import dao.ClientDaoImpl;
import exceptions.NoConnectionJDBCException;
import pojo.Client;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервисный класс для клиента со свойствами <b>service</b> и <b>clientDaoImpl</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class ClientService {
    /**
     * Статическое поле сервесного класса {@link ClientService} для реализации Singleton
     */
    private static ClientService instance;

    /**
     * Поле ссылки на объект {@link ClientDaoImpl}
     */
    private ClientDaoImpl clientDaoImpl;


    /**
     * Статическая функция получения значения поля {@link ClientService#instance}
     *
     * @return возвращает экземпляр класса {@link ClientService}
     * @throws NoConnectionJDBCException - при неправильном подключении к бд
     */
    public static ClientService getInstance() throws NoConnectionJDBCException {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
    }

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     *
     * @throws NoConnectionJDBCException - при неправильном подключении к бд
     */
    private ClientService() throws NoConnectionJDBCException {
        try {
            clientDaoImpl = ClientDaoImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");

        }
    }


    /**
     * Функция добавление нового клиента и получения его идентификатора {@link Client#getId()}
     *
     * @param client - объект добавляемого клиента
     * @return возвращает идентификатор добавляемого клиента
     */
    public Integer addClient(Client client) {
        int maxUserId = clientDaoImpl.getMaxClientId();
        if (maxUserId != 0) {
            client.setId(maxUserId + 1);
        } else {
            client.setId(1);
        }
        clientDaoImpl.create(client);
        return client.getId();
    }

    /**
     * Функция получения всех клиентов из списка {@link Client}
     *
     * @return возвращает список клиентов
     */
    public List<Client> getAllClients() {
        List<Client> clients;
        clients = clientDaoImpl.readAll();
        return clients;
    }
}
