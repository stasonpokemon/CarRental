package menu;

import exceptions.NoConnectionJDBCException;
import pojo.Car;
import pojo.Client;
import pojo.Order;
import services.CarService;
import services.ClientService;
import services.OrderService;
import utils.LanguagePropertyLoader;
import utils.NumberValidUtil;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderCreationMenu implements Menu {

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
        Order order;
        Car car;
        Client client;


//        -Клиент выбирает автомобиль
        System.out.println(LanguagePropertyLoader.getProperty("OCM_ENTER_CAR_NAME"));
        String carName = scanner.nextLine();
//        -Клиент называет имя, фамилию и адрес
        System.out.println(LanguagePropertyLoader.getProperty("OCM_ENTER_CLIENT_NAME"));
        String clientName = scanner.nextLine();
        System.out.println(LanguagePropertyLoader.getProperty("OCM_ENTER_CLIENT_ADDRESS"));
        String clientAddress = scanner.nextLine();
//        -Клиент указывает срок аренды
        int rentalPeriod = 0;
        rentalPeriod = NumberValidUtil.getOperationNumberUtil().intPositiveNumberValid(rentalPeriod, LanguagePropertyLoader.getProperty("OCM_ENTER_RENTAL_PERIOD"));
        double carPricePerDay = 0;
        carPricePerDay = NumberValidUtil.getOperationNumberUtil().doublePositiveNumberValid(carPricePerDay, LanguagePropertyLoader.getProperty("OCM_ENTER_CAR_PRICE"));
//        Стоимость заказа рассчитывается из указанной стоимости автомобиля за сутки и срока аренды
        double orderPrice = carPricePerDay * rentalPeriod;
        do {
            operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("OCM_ORDER_CONFIRMATION"));
            switch (operationNumber) {
                case 1:
                    try {
                        order = new Order();
                        car = new Car();

                        car.setName(carName);
                        car.setState("WITHOUT_DAMAGE");
                        CarService.getService().addNewCar(car);
//                        Метод, который проверяет, есть ли клиент с введёнными данными в бд. Если такой клиент имеется, то мы не создаём новый объект клиента
                        client = searchClientInDb(clientName, clientAddress);


                        order.setPrice(orderPrice);
                        order.setState("Approved");
                        order.setDate(new Timestamp(new GregorianCalendar().getTimeInMillis()));
                        order.setTime(rentalPeriod);
                        order.setCar(car);
                        order.setClient(client);
                        OrderService.getOrderService().addOrder(order);
                        System.out.println(LanguagePropertyLoader.getProperty("OCM_ORDER_APPROVED"));
                    } catch (NoConnectionJDBCException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    break;
                case 2:
                    try {
                        order = new Order();
                        car = new Car();

                        car.setName(carName);
                        car.setState("WITHOUT_DAMAGE");
                        CarService.getService().addNewCar(car);

 //                        Метод, который проверяет, есть ли клиент с введёнными данными в бд. Если такой клиент имеется, то мы не создаём новый объект клиента
                        client = searchClientInDb(clientName, clientAddress);

                        order.setState("Declined");
                        order.setDate(new Timestamp(new GregorianCalendar().getTimeInMillis()));
                        order.setTime(rentalPeriod);
                        order.setCar(car);
                        order.setClient(client);
                        OrderService.getOrderService().addOrder(order);
                        System.out.println(LanguagePropertyLoader.getProperty("OCM_ORDER_DECLINED"));
                    } catch (NoConnectionJDBCException e) {
                        e.printStackTrace();

                    }
                    exit = true;
                    break;
                default:
                    System.out.println(LanguagePropertyLoader.getProperty("OCM_NO_OPERATION"));
                    break;
            }
        } while (!exit);
    }


    private Client searchClientInDb(String clientName, String clientAddress) throws NoConnectionJDBCException {
        Client client;
        final List<Client> collect = ClientService.getService().getAllClients().stream().filter(name -> name.getName().equals(clientName)).filter(address -> address.getAddress().equals(clientAddress)).collect(Collectors.toList());
        if (collect.size() == 0) {
            client = new Client();
            client.setName(clientName);
            client.setAddress(clientAddress);
            ClientService.getService().addClient(client);
        } else {
            client = collect.get(0);
        }
        return client;
    }
}
