package services;

import dao.ClientDaoImpl;
import pojo.Client;

import java.sql.SQLException;

public class ClientService {
    private static ClientService service;

    public static ClientService getService() {
        if (service == null){
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
}
