package services;

import dao.CarDaoImpl;
import dao.ClientDaoImpl;
import exceptions.NoConnectionJDBCException;
import pojo.Car;
import pojo.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static ClientService service;

    public static ClientService getService() {
        if (service == null) {
            service = new ClientService();
        }
        return service;
    }

    /*
     * Регистрация нового клиента
     * */
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

    public List<Client> getAllClients() throws NoConnectionJDBCException {
        List<Client> clients = new ArrayList<>();
        try {
            clients = ClientDaoImpl.getInstance().readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
        return clients;
    }
}
