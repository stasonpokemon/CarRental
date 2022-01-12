package services;

import dao.OrderDaoImpl;
import exceptions.NoConnectionJDBCException;
import pojo.Car;
import pojo.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервесный класс для заказа со свойствами <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class OrderService {
    /**
     * Статическое поле сервесного класса {@link OrderService} для реализации Singleton
     */
    private static OrderService instance;

    /**
     * Статическая функция получения значения поля {@link OrderService#instance}
     *
     * @return возвращает экземпляр класса {@link OrderService}
     */
    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    private OrderService() {
    }

    /**
     * Функция добавление нового заказа {@link Car#getId()}
     *
     * @param order - объект добавляемого заказа
     * @throws NoConnectionJDBCException - при неправильном поключении к бд
     */
    public void addOrder(Order order) throws NoConnectionJDBCException {
        try {
            Integer maxOrderId = OrderDaoImpl.getInstance().getMaxOrderId();
            if (maxOrderId != null) {
                order.setId(maxOrderId + 1);
            } else {
                order.setId(1);
            }
            if (order.getRefund() == null) {
                OrderDaoImpl.getInstance().create(order);
            } else {
                OrderDaoImpl.getInstance().createWithRefund(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция получения всех заказов из списка {@link Order}
     *
     * @return возвращает список заказов
     * @throws NoConnectionJDBCException - при неправильном поключении к бд
     */
    public List<Order> findAllOrders() throws NoConnectionJDBCException {
        try {
            return OrderDaoImpl.getInstance().readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
    }


    /**
     * Функция получения всех одобренных заказов без возврата из списка {@link Order}
     *
     * @return возвращает список одобренных заказов без возврата
     * @throws NoConnectionJDBCException - при неправильном поключении к бд
     */
    public List<Order> findApprovedOrdersWithoutRefund() throws NoConnectionJDBCException {
        try {
            return OrderDaoImpl.getInstance().readApprovedOrdersWithoutRefund();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
    }


    /**
     * Функция обновления заказа
     *
     * @param order - объект обновляемого заказа
     * @throws SQLException - при неправильном подключении к бд
     */
    public void update(Order order) {
        try {
            OrderDaoImpl.getInstance().update(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
