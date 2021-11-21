package services;

import dao.CarDaoImpl;
import pojo.Car;

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
    public Integer addNewCar(Car car) {
        int maxCarId = CarDaoImpl.getCarDao().getMaxCarId();
        if (maxCarId != 0){
            car.setId(maxCarId + 1);
        }else {
            car.setId(1);
        }
        CarDaoImpl.getCarDao().create(car);

        return car.getId();
    }
}
