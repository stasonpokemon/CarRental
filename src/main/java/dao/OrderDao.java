package dao;

import pojo.Client;
import pojo.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {

    /*
     * Добавление заказа в бд с возвратом
     * */
    Integer createWithRefund(Order order);

    /*
     * Список одобренных заказов без возврата
     * */
    List<Order> readApprovedOrdersWithoutRefund();

    /*
     * Получение максимального id из всех заказов
     * */
    Integer getMaxOrderId();

    void update(Order order);

    List<Order> readAll();


}
