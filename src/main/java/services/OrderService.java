package services;

import dao.OrderDaoImpl;
import exceptions.NoConnectionJDBCException;
import pojo.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static OrderService orderService;

    private OrderService() {
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
                orderService = new OrderService();
        }
        return orderService;
    }

    /*
     * Список всех заказов
     * */
    public List<Order> findAllOrders() throws NoConnectionJDBCException {
        try {
            return OrderDaoImpl.getInstance().readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
    }

    /*
     * Список одобренных заказов без возврата
     * */
    public List<Order> findApprovedOrdersWithoutRefund() throws NoConnectionJDBCException {
        try {
            return OrderDaoImpl.getInstance().readApprovedOrdersWithoutRefund();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
    }

    /*
     * Создание нового заказа
     * */
    public void addOrder(Order order) throws NoConnectionJDBCException {
        try {
            Integer maxOrderId = OrderDaoImpl.getInstance().getMaxOrderId();
            if (maxOrderId != null){
                order.setId(maxOrderId + 1);
            }else {
                order.setId(1);
            }
            if (order.getRefund() == null){
                OrderDaoImpl.getInstance().create(order);
            }else {
                OrderDaoImpl.getInstance().createWithRefund(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Order order) {
        try {
            OrderDaoImpl.getInstance().update(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
