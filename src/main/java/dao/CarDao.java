package dao;

import pojo.Car;

import java.util.List;

public interface CarDao extends Dao<Car> {
    int getMaxCarId();

    List<Car> readAll();


}
