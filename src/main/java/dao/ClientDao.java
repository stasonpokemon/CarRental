package dao;

import pojo.Client;

public interface ClientDao extends Dao<Client> {
    /*
     * Получить максимальный id users
     * */

    int getMaxClientId();
}
