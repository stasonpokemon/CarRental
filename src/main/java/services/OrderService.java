package services;

import dao.CarDaoImpl;
import dao.OrderDaoImpl;
import exceptions.NoConnectionJDBCException;
import pojo.Car;
import pojo.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервисный класс для заказа со свойствами <b>instance</b> и <b>orderDaoImpl</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class OrderService {
    /**
     * Статическое поле сервисного класса {@link OrderService} для реализации Singleton
     */
    private static OrderService instance;

    /**
     * Поле ссылки на объект {@link OrderDaoImpl}
     */
    private OrderDaoImpl orderDaoImpl;

    /**
     * Статическая функция получения значения поля {@link OrderService#instance}
     *
     * @return возвращает экземпляр класса {@link OrderService}
     * @throws NoConnectionJDBCException - при неправильном подключении к бд
     */
    public static OrderService getInstance() throws NoConnectionJDBCException {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     *
     * @throws NoConnectionJDBCException - при неправильном подключении к бд
     */
    private OrderService() throws NoConnectionJDBCException {
        try {
            orderDaoImpl = OrderDaoImpl.getInstance();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
    }

    /**
     * Функция добавление нового заказа {@link Car#getId()}
     *
     * @param order - объект добавляемого заказа
     * @throws NoConnectionJDBCException - при неправильном поключении к бд
     */
    public void addOrder(Order order) throws NoConnectionJDBCException {
        try {
            Integer maxOrderId = orderDaoImpl.getMaxOrderId();
            if (maxOrderId != null) {
                order.setId(maxOrderId + 1);
            } else {
                order.setId(1);
            }
            if (order.getRefund() == null) {
                orderDaoImpl.create(order);
            } else {
                orderDaoImpl.createWithRefund(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция получения всех заказов из списка {@link Order}
     *
     * @return возвращает список заказов
     */
    public List<Order> findAllOrders() {
        return orderDaoImpl.readAll();
    }


    /**
     * Функция получения всех одобренных заказов без возврата из списка {@link Order}
     *
     * @return возвращает список одобренных заказов без возврата
     */
    public List<Order> findApprovedOrdersWithoutRefund() {
        return orderDaoImpl.readApprovedOrdersWithoutRefund();
    }


    /**
     * Функция обновления заказа
     *
     * @param order - объект обновляемого заказа
     */
    public void update(Order order) {
        orderDaoImpl.update(order);
    }
}
