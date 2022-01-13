package services;

import dao.CarDaoImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pojo.Car;


import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest extends TestCase {
    @Mock
    private CarDaoImpl carDao;
    @InjectMocks
    private CarService carService;

    @Test
    public void testAddNewCar() {
        Car car = mock(Car.class);
        carService.addNewCar(car);
        verify(carDao).create(car);
    }

    @Test
    public void testGetAllCars() {
        carService.getAllCars();
        verify(carDao).readAll();

    }
}
