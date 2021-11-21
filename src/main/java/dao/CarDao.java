package dao;

import pojo.Car;

public interface CarDao extends Dao<Car>{
    int getMaxCarId();

}
