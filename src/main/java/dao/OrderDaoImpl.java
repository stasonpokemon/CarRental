package dao;

import exceptions.NoConnectionJDBCException;
import pojo.Car;
import pojo.Client;
import pojo.Order;
import pojo.Refund;
import services.CarService;
import services.ClientService;
import services.RefundService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO класс для соединения сущности {@link Order} и базы данных
 * наследуемый от класса {@link BaseDaoImpl} и реализующий интерфейс {@link OrderDao}
 * со свойствами <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    /**
     * Статическое поле DAO класса {@link OrderDaoImpl} для реализации Singleton
     */
    private static OrderDaoImpl instance;

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     *
     * @throws SQLException
     */
    public OrderDaoImpl() throws SQLException {
    }

    /**
     * Статическая функция получения значения поля {@link OrderDaoImpl#instance}
     *
     * @return возвращает экземпляр класса {@link OrderDaoImpl}
     * @throws SQLException
     */
    public static OrderDaoImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    /**
     * Функция создания(добавления) нового заказа в таблицу orders
     *
     * @return возвращает id созданного заказа
     * @throws NoConnectionJDBCException
     * @throws SQLException
     */
    @Override
    public Integer create(Order order) throws NoConnectionJDBCException, SQLException {
        String sql = "INSERT INTO orders (id, price, state, date, time, car_id, client_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.setDouble(2, order.getPrice());
            statement.setString(3, order.getState());
            statement.setTimestamp(4, order.getDate());
            statement.setInt(5, order.getTime());
            if (order.getCar().getId() == null) {
                Car newCar = new Car();
                newCar.setName(order.getCar().getName());
                newCar.setState(order.getCar().getState());
//                Добавить авто в бд с новым id
                Integer carId = CarService.getInstance().addNewCar(newCar);
                statement.setInt(6, newCar.getId());
            } else {
                statement.setInt(6, order.getCar().getId());
            }
            if (order.getClient().getId() == null) {
                Client newClient = new Client();
                newClient.setName(order.getClient().getName());
                newClient.setAddress(order.getClient().getAddress());
//                Добавить клиента в бд с новым id
                final Integer clientId = ClientService.getInstance().addClient(newClient);
                statement.setInt(7, clientId);
            } else {
                statement.setInt(7, order.getClient().getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order.getId();
    }

    /**
     * Функция создания(добавления) нового заказа(из json) в таблицу orders, который включает в себя возврат автомобиля
     *
     * @return возвращает id созданного заказа
     * @throws NoConnectionJDBCException
     * @throws SQLException
     */
    @Override
    public Integer createWithRefund(Order order) throws NoConnectionJDBCException {
        String sql = "INSERT INTO orders (id, price, state, date, time, car_id, client_id, refund_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.setDouble(2, order.getPrice());
            statement.setString(3, order.getState());
            statement.setTimestamp(4, order.getDate());
            statement.setInt(5, order.getTime());
            if (order.getCar().getId() == null) {
                Car newCar = new Car();
                newCar.setName(order.getCar().getName());
                newCar.setState(order.getCar().getState());
//                Добавить авто в бд с новым id
                final Integer carId = CarService.getInstance().addNewCar(newCar);
                statement.setInt(6, carId);
            } else {
                statement.setInt(6, order.getCar().getId());
            }

            if (order.getClient().getId() == null) {
                Client newClient = new Client();
                newClient.setName(order.getClient().getName());
                newClient.setAddress(order.getClient().getAddress());
//                Добавить клиента в бд с новым id
                final Integer clientId = ClientService.getInstance().addClient(newClient);
                statement.setInt(7, clientId);
            } else {
                statement.setInt(7, order.getClient().getId());
            }

            if (order.getRefund().getId() == null) {
                Refund newRefund = new Refund();
                newRefund.setDetail(order.getRefund().getDetail());
                newRefund.setState(order.getRefund().getState());
                newRefund.setPrice(order.getRefund().getPrice());
//                Добавит заказ в бд с новым id
                final Integer refundId = RefundService.getInstance().addNewRefund(newRefund);
                statement.setInt(8, refundId);
            } else {
                statement.setInt(8, order.getRefund().getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order.getId();
    }

    /**
     * Функция получения всех заказов из таблицы orders
     *
     * @return возвращает коллекцию заказов
     */
    @Override
    public List<Order> readAll() {
        String sqlOrder = "SELECT id, price, state, date, time, car_id, client_id, refund_id FROM orders";
        String sqlCar = "SELECT id, name, state FROM cars WHERE id = ?";
        String sqlClient = "SELECT id, name, address FROM clients WHERE id = ?";
        String sqlRefund = "SELECT id, state, detail, price FROM refunds WHERE id = ?";
        List<Order> orders = new ArrayList<>();

        PreparedStatement statementOrder = null;
        PreparedStatement statementClient = null;
        PreparedStatement statementCar = null;
        PreparedStatement statementRefund = null;

        ResultSet resultSetOrder = null;
        ResultSet resultSetClient = null;
        ResultSet resultSetCar = null;
        ResultSet resultSetRefund = null;

        Order order = null;
        Client client = null;
        Car car = null;
        Refund refund = null;

        try {
            statementOrder = connection.prepareStatement(sqlOrder);
            statementClient = connection.prepareStatement(sqlClient);
            statementCar = connection.prepareStatement(sqlCar);
            statementRefund = connection.prepareStatement(sqlRefund);

            resultSetOrder = statementOrder.executeQuery();
            while (resultSetOrder.next()) {
                order = new Order();
                order.setId(resultSetOrder.getInt("id"));
                order.setPrice(resultSetOrder.getDouble("price"));
                order.setState(resultSetOrder.getString("state"));
                order.setDate(resultSetOrder.getTimestamp("date"));
                order.setTime(resultSetOrder.getInt("time"));

                final int carId = resultSetOrder.getInt("car_id");
                statementCar.setInt(1, carId);
                resultSetCar = statementCar.executeQuery();
                if (!resultSetOrder.wasNull()) {
                    if (resultSetCar.next()) {
                        car = new Car();
                        car.setId(resultSetCar.getInt("id"));
                        car.setName(resultSetCar.getString("name"));
                        car.setState(resultSetCar.getString("state"));
                    }
                    order.setCar(car);
                }

                final int client_id = resultSetOrder.getInt("client_id");
                statementClient.setInt(1, client_id);
                resultSetClient = statementClient.executeQuery();
                if (!resultSetOrder.wasNull()) {
                    if (resultSetClient.next()) {
                        client = new Client();
                        client.setId(resultSetClient.getInt("id"));
                        client.setName(resultSetClient.getString("name"));
                        client.setAddress(resultSetClient.getString("address"));
                    }
                    order.setClient(client);
                }

                final int refund_id = resultSetOrder.getInt("refund_id");
                statementRefund.setInt(1, refund_id);
                resultSetRefund = statementRefund.executeQuery();
                if (!resultSetOrder.wasNull()) {
                    if (resultSetRefund.next()) {
                        refund = new Refund();
                        refund.setId(resultSetRefund.getInt("id"));
                        refund.setState(resultSetRefund.getString("state"));
                        refund.setDetail(resultSetRefund.getString("detail"));
                        refund.setPrice(resultSetRefund.getDouble("price"));
                    }
                    order.setRefund(refund);
                }
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    /**
     * Функция получения одобренных заказов без возврата из таблицы orders
     *
     * @return возвращает коллекцию заказов
     */
    @Override
    public List<Order> readApprovedOrdersWithoutRefund() {
        String sqlOrder = "SELECT id, price, state, date, time, car_id, client_id, refund_id FROM orders WHERE state LIKE 'Approved' AND refund_id IS NULL";
        String sqlCar = "SELECT id, name, state FROM cars WHERE id = ?";
        String sqlClient = "SELECT id, name, address FROM clients WHERE id = ?";
        List<Order> orders = new ArrayList<>();

        PreparedStatement statementOrder = null;
        PreparedStatement statementClient = null;
        PreparedStatement statementCar = null;

        ResultSet resultSetOrder = null;
        ResultSet resultSetClient = null;
        ResultSet resultSetCar = null;

        Order order = null;
        Client client = null;
        Car car = null;

        try {
            statementOrder = connection.prepareStatement(sqlOrder);
            statementClient = connection.prepareStatement(sqlClient);
            statementCar = connection.prepareStatement(sqlCar);

            resultSetOrder = statementOrder.executeQuery();
            while (resultSetOrder.next()) {
                order = new Order();
                order.setId(resultSetOrder.getInt("id"));
                order.setPrice(resultSetOrder.getDouble("price"));
                order.setState(resultSetOrder.getString("state"));
                order.setDate(resultSetOrder.getTimestamp("date"));
                order.setTime(resultSetOrder.getInt("time"));

                final int carId = resultSetOrder.getInt("car_id");
                statementCar.setInt(1, carId);
                resultSetCar = statementCar.executeQuery();
                if (!resultSetOrder.wasNull()) {
                    if (resultSetCar.next()) {
                        car = new Car();
                        car.setId(resultSetCar.getInt("id"));
                        car.setName(resultSetCar.getString("name"));
                        car.setState(resultSetCar.getString("state"));
                    }
                    order.setCar(car);
                }

                final int client_id = resultSetOrder.getInt("client_id");
                statementClient.setInt(1, client_id);
                resultSetClient = statementClient.executeQuery();
                if (!resultSetOrder.wasNull()) {
                    if (resultSetClient.next()) {
                        client = new Client();
                        client.setId(resultSetClient.getInt("id"));
                        client.setName(resultSetClient.getString("name"));
                        client.setAddress(resultSetClient.getString("address"));
                    }
                    order.setClient(client);
                }
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    /**
     * Функция обновления состояния заказа
     * @param order заказ, у которого нужно обновить состояние
     */
    @Override
    public void update(Order order) {
        String sql = "UPDATE orders SET  price = ?, state = ?, date = ?, time = ?, car_id = ?, client_id = ?, refund_id = ?  WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, order.getPrice());
            statement.setString(2, order.getState());
            statement.setTimestamp(3, order.getDate());
            statement.setInt(4, order.getTime());
            statement.setInt(5, order.getCar().getId());
            statement.setInt(6, order.getClient().getId());
            statement.setInt(7, order.getRefund().getId());
            statement.setInt(8, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция получения максимального значения id в таблице orders
     *
     * @return возвращает максимальное значение id
     */
    @Override
    public Integer getMaxOrderId() {
        String sql = "SELECT MAX(id) as 'max' FROM orders";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer id = 0;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("max");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

}
