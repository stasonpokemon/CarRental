package services;

import dao.CarDaoImpl;
import exceptions.NoConnectionJDBCException;
import pojo.Car;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервесный класс для автомобиля со свойствами <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class CarService {

    /**
     * Статическое поле сервесного класса {@link CarService} для реализации Singleton
     */
    private static CarService instance;

    /**
     * Статическая функция получения значения поля {@link CarService#instance}
     *
     * @return возвращает экземпляр класса {@link CarService}
     */
    public static CarService getInstance() {
        if (instance == null) {
            instance = new CarService();
        }
        return instance;
    }

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    private CarService() {
    }


    /**
     * Функция добавление нового автомобиля и получения его идентификатора {@link Car#getId()}
     *
     * @param car - объект добавляемого автомобиля
     * @return возвращает идентификатор добавляемого автомобиля
     * @throws NoConnectionJDBCException - при неправильном поключении к бд
     */
    public Integer addNewCar(Car car) throws NoConnectionJDBCException {
        try {
            int maxCarId = CarDaoImpl.getInstance().getMaxCarId();
            if (maxCarId != 0) {
                car.setId(maxCarId + 1);
            } else {
                car.setId(1);
            }
            CarDaoImpl.getInstance().create(car);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
        return car.getId();
    }

    /**
     * Функция получения всех автомобилей из списка {@link Car}
     *
     * @return возвращает список автомобилей
     * @throws NoConnectionJDBCException - при неправильном поключении к бд
     */
    public List<Car> getAllCars() throws NoConnectionJDBCException {
        List<Car> cars;
        try {
            cars = CarDaoImpl.getInstance().readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
        return cars;
    }
}
