package services;

import dao.CarDaoImpl;
import exceptions.NoConnectionJDBCException;
import pojo.Car;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервисный класс для автомобиля со свойствами <b>instance</b> и <b>carDaoImpl</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class CarService {

    /**
     * Статическое поле сервисного класса {@link CarService} для реализации Singleton
     */
    private static CarService instance;

    /**
     * Поле ссылки на объект {@link CarDaoImpl}
     */
    private CarDaoImpl carDaoImpl;

    /**
     * Статическая функция получения значения поля {@link CarService#instance}
     *
     * @return возвращает экземпляр класса {@link CarService}
     * @throws NoConnectionJDBCException - при неправильном подключении к бд
     */
    public static CarService getInstance() throws NoConnectionJDBCException {
        if (instance == null) {
            instance = new CarService();
        }
        return instance;
    }

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     *
     * @throws NoConnectionJDBCException - при неправильном подключении к бд
     */
    private CarService() throws NoConnectionJDBCException {
        try {
            carDaoImpl = CarDaoImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
    }


    /**
     * Функция добавление нового автомобиля и получения его идентификатора {@link Car#getId()}
     *
     * @param car - объект добавляемого автомобиля
     * @return возвращает идентификатор добавляемого автомобиля
     */
    public Integer addNewCar(Car car) {
        int maxCarId = carDaoImpl.getMaxCarId();
        if (maxCarId != 0) {
            car.setId(maxCarId + 1);
        } else {
            car.setId(1);
        }
        carDaoImpl.create(car);
        return car.getId();
    }

    /**
     * Функция получения всех автомобилей из списка {@link Car}
     *
     * @return возвращает список автомобилей
     */
    public List<Car> getAllCars() {
        List<Car> cars;
        cars = carDaoImpl.readAll();
        return cars;
    }
}
