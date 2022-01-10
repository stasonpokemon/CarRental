package services;

import dao.ClientDaoImpl;
import exceptions.NoConnectionJDBCException;
import pojo.Client;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервесный класс для клиента со свойствами <b>service</b>.
 *
 * @version 1.1
 * @autor Stanislav Trebnikov
 */
public class ClientService {
    /**
     * Статическое поле сервесного класса {@link ClientService} для реализации Singleton
     */
    private static ClientService instance;

    /**
     * Статическая функция получения значения поля {@link ClientService#instance}
     *
     * @return возвращает экземпляр класса {@link ClientService}
     */
    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
    }

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    private ClientService() {
    }


    /**
     * Функция добавление нового клиента и получения его идентификатора {@link Client#getId()}
     *
     * @param client - объект добавляемого клиента
     * @return возвращает идентификатор добавляемого клиента
     * @throws SQLException
     */
    public Integer addClient(Client client) {
        try {
            int maxUserId = ClientDaoImpl.getInstance().getMaxClientId();
            if (maxUserId != 0) {
                client.setId(maxUserId + 1);
            } else {
                client.setId(1);
            }
            ClientDaoImpl.getInstance().create(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client.getId();
    }

    /**
     * Функция получения всех клиентов из списка {@link Client}
     *
     * @return возвращает список клиентов
     * @throws NoConnectionJDBCException - при неправильном поключении к бд
     */
    public List<Client> getAllClients() throws NoConnectionJDBCException {
        List<Client> clients;
        try {
            clients = ClientDaoImpl.getInstance().readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
        return clients;
    }
}
