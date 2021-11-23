package menu;

import exceptions.NoConnectionJDBCException;
import pojo.Car;
import pojo.Client;
import pojo.Order;
import services.CarService;
import services.ClientService;
import services.OrderService;
import utils.NumberValidUtil;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class OrderCreationMenu extends Menu {
    private static final String ORDER_CONFIRMATION = "Подтверждение заказа:\n" +
            "1. Одобрить\n" +
            "2. Отклонить";
    private static final String ENTER_CAR_NAME = "Укажите название выбранного автомобиля:";
    private static final String ENTER_CLIENT_NAME = "Укажите имя и фамилию клиента:";
    private static final String ENTER_CLIENT_ADDRESS = "Укажите адрес клиента:";
    private static final String ENTER_RENTAL_PERIOD = "Введите срок арнеды:";
    private static final String ORDER_APPROVED = "Заказ принят...";
    private static final String ORDER_DECLINED = "Заказ отклонён...";
    private static final String NO_OPERATION = "Не существует введённой вами операции, попробуйте ещё раз...";
    private static final String ENTER_CAR_PRICE = "Введите стоимость автомобиля за сутки:";


    private static OrderCreationMenu menu;
    private final Scanner scanner = new Scanner(System.in);
    private int operationNumber;

    private OrderCreationMenu() {
    }

    public static OrderCreationMenu getInstance() {
        if (menu == null) {
            menu = new OrderCreationMenu();
        }
        return menu;
    }


    @Override
    public void getMenu() {
        boolean exit = false;
        Order order = new Order();
        Car car = new Car();
        Client client = new Client();


//        -Клиент выбирает автомобиль
        System.out.println(ENTER_CAR_NAME);
        String carName = scanner.nextLine();
//        -Клиент называет имя, фамилию и адрес
        System.out.println(ENTER_CLIENT_NAME);
        String clientName = scanner.nextLine();
        System.out.println(ENTER_CLIENT_ADDRESS);
        String clientAddress = scanner.nextLine();
//        -Клиент указывает срок аренды
        System.out.println(ENTER_RENTAL_PERIOD);
        int rentalPeriod = scanner.nextInt();
        System.out.println(ENTER_CAR_PRICE);
        double carPricePerDay = scanner.nextDouble();
//        Стоимость заказа рассчитывается из указанной стоимости автомобиля за сутки и срока аренды
        double orderPrice = carPricePerDay * rentalPeriod;
        do {
            operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, ORDER_CONFIRMATION);
            switch (operationNumber) {
                case 1:
                    try {
                        car.setName(carName);
                        car.setState("WITHOUT_DAMAGE");
                        CarService.getService().addNewCar(car);

                        client.setName(clientName);
                        client.setAddress(clientAddress);
                        ClientService.getService().addClient(client);

                        order.setPrice(orderPrice);
                        order.setState("Approved");
                        order.setDate(new Timestamp(new GregorianCalendar().getTimeInMillis()));
                        order.setTime(rentalPeriod);
                        order.setCar(car);
                        order.setClient(client);
                        OrderService.getOrderService().addOrder(order);
                        System.out.println(ORDER_APPROVED);
                    } catch (NoConnectionJDBCException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    break;
                case 2:
                    try {
                        car.setName(carName);
                        car.setState("WITHOUT_DAMAGE");
                        CarService.getService().addNewCar(car);

                        client.setName(clientName);
                        client.setAddress(clientAddress);
                        ClientService.getService().addClient(client);

                        order.setState("Declined");
                        order.setDate(new Timestamp(new GregorianCalendar().getTimeInMillis()));
                        order.setTime(rentalPeriod);
                        order.setCar(car);
                        order.setClient(client);
                        OrderService.getOrderService().addOrder(order);
                        System.out.println(ORDER_DECLINED);
                    } catch (NoConnectionJDBCException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    break;
                default:
                    System.out.println(NO_OPERATION);
                    break;
            }
        } while (!exit);
    }
}
