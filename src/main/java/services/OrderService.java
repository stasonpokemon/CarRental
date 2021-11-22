package services;

import dao.OrderDaoImpl;
import pojo.Client;
import pojo.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderDaoImpl orderDaoImpl = OrderDaoImpl.getOrderDao();

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
     * Список одобренных заказов без возврата
     * */
    public List<Order> findApprovedOrdersWithoutRefund(){
        return orderDaoImpl.readApprovedOrdersWithoutRefund();
    }


    /*
     * Список заказов определённого клиента
     * */
    public List<Order> findAllOrdersByClient(Client client) {
        return orderDaoImpl.findAllOrdersByClient(client);
    }



    /*
     * Создание нового заказа
     * */
    public void addOrder(Order order) {
        Integer maxOrderId = orderDaoImpl.getMaxOrderId();
        if (maxOrderId != null){
            order.setId(maxOrderId + 1);
            if (order.getRefund() == null){
                orderDaoImpl.create(order);
            }else {
                orderDaoImpl.createWithRefund(order);
            }
        }else {
            order.setId(1);
            if (order.getRefund() == null){
                orderDaoImpl.create(order);
            }else {
                orderDaoImpl.createWithRefund(order);
            }
        }
    }

    public void update(Order order) {
        orderDaoImpl.update(order);
    }

}
