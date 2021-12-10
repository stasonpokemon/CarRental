package services;

import dao.CarDaoImpl;
import exceptions.NoConnectionJDBCException;
import pojo.Car;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarService {
    private static CarService service;

    public static CarService getService() {
        if (service == null){
            service = new CarService();
        }
        return service;
    }

    /*
     * Добавление нового автомобиля
     * */
    public Integer addNewCar(Car car) throws NoConnectionJDBCException {
        try {
            int maxCarId = CarDaoImpl.getInstance().getMaxCarId();
            if (maxCarId != 0){
                car.setId(maxCarId + 1);
            }else {
                car.setId(1);
            }
            CarDaoImpl.getInstance().create(car);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
        return car.getId();
    }

    public List<Car> getAllCars() throws NoConnectionJDBCException {
        List<Car> cars = new ArrayList<>();
        try {
            cars = CarDaoImpl.getInstance().readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
        return cars;
    }
}
