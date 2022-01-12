package dao;

import pojo.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO класс для соединения сущности {@link Car} и базы данных
 * наследуемый от класса {@link BaseDaoImpl} и реализующий интерфейс {@link CarDao}
 * со свойствами <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class CarDaoImpl extends BaseDaoImpl implements CarDao {
    /**
     * Статическое поле DAO класса {@link CarDaoImpl} для реализации Singleton
     */
    private static CarDaoImpl instance;

    /**
     * Статическая функция получения значения поля {@link CarDaoImpl#instance}
     *
     * @return возвращает экземпляр класса {@link CarDaoImpl}
     * @throws SQLException
     */
    public static CarDaoImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new CarDaoImpl();
        }
        return instance;
    }

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     *
     * @throws SQLException
     */
    private CarDaoImpl() throws SQLException {
    }

    /**
     * Функция получения максимального значения id в таблице cars
     *
     * @return возвращает максимальное значение id
     */
    @Override
    public int getMaxCarId() {
        String sql = "SELECT MAX(id) as 'max' FROM cars";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = 0;
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

    /**
     * Функция получения всех автомобилей из таблицы cars
     *
     * @return возвращает коллекцию автомобилей
     */
    @Override
    public List<Car> readAll() {
        String sql = "SELECT id, name, state FROM cars";
        PreparedStatement statement;
        ResultSet resultSet;
        List<Car> cars = new ArrayList<>();
        Car car;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setName(resultSet.getString("name"));
                car.setState(resultSet.getString("state"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    /**
     * Функция создания(добавления) нового автомобиля в таблицу cars
     *
     * @return возвращает id созданного автомобиля
     */
    @Override
    public Integer create(Car car) {
        String sql = "INSERT INTO cars (id, name, state) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, car.getId());
            statement.setString(2, car.getName());
            statement.setString(3, car.getState());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car.getId();
    }
}
