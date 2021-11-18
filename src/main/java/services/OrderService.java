package services;

import dao.OrderDaoImpl;
import pojo.Client;
import pojo.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderDaoImpl orderDaoImpl = OrderDaoImpl.getOrderDaoImpl();

    private static OrderService orderService;

    public OrderService() throws SQLException {
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            try {
                orderService = new OrderService();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderService;
    }

    /*
     * Список всех заказов
     * */
    public List<Order> findAllOrders() {
        return orderDaoImpl.readAll();
    }

    /*
     * Список заказов определённого клиента
     * */
    public List<Order> findAllOrdersByClient(Client client) {
        return orderDaoImpl.findAllOrdersByClient(client);
    }


    /*
     * Список заказов с определённым статусом
     * */
    public List<Order> findOrdersByStatus(String state) {
        return orderDaoImpl.findOrdersByStatus(state);
    }

    /*
     * Создание нового заказа
     * */
    public void addOrder(Order order) {
        int maxOrderId = orderDaoImpl.getMaxOrderId();
        if (maxOrderId != 0){
            order.setId(maxOrderId + 1);
        }else {
            order.setId(1);
        }
        orderDaoImpl.create(order);
    }

    public void update(Order order) {
        orderDaoImpl.update(order);
    }

    public void updateWithoutRefund(Order order) {
        orderDaoImpl.updateWithoutRefund(order);
    }

}
