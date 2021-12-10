package dao;

import pojo.Car;
import pojo.Client;

import java.util.List;

public interface ClientDao extends Dao<Client> {
    /*
     * Получить максимальный id users
     * */

    int getMaxClientId();
    List<Client> readAll();

}
