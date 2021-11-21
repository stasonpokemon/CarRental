package services;

import dao.ClientDaoImpl;
import pojo.Client;

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
        int maxUserId = ClientDaoImpl.getClientDao().getMaxClientId();
        if (maxUserId != 0) {
            client.setId(maxUserId + 1);
        } else {
            client.setId(1);
        }
        ClientDaoImpl.getClientDao().create(client);
        return client.getId();
    }
}
