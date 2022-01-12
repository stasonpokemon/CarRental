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

/**
 * Класс меню, который позволяет реализовать создание заказа, реализующий интерфейс {@link Menu}, со свойствами <b>operationNumber</b> и <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class OrderCreationMenu implements Menu {

    /**
     * Поле сканера для чтения строк из консоли
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Поле номера выбора операции
     */
    private int operationNumber;

    /**
     * Статическое поле класса меню {@link OrderCreationMenu} для реализации Singleton
     */
    private static OrderCreationMenu instance;

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    private OrderCreationMenu() {
    }

    /**
     * Статическая функция получения значения поля {@link OrderCreationMenu#instance}
     *
     * @return возвращает экземпляр класса {@link OrderCreationMenu}
     */
    public static OrderCreationMenu getInstance() {
        if (instance == null) {
            instance = new OrderCreationMenu();
        }
        return instance;
    }

    /**
     * Функция вызова меню, которая позволяет реализовать создание заказа
     */
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
        rentalPeriod = NumberValidUtil.getInstance().intPositiveNumberValid(rentalPeriod, LanguagePropertyLoader.getProperty("OCM_ENTER_RENTAL_PERIOD"));
        double carPricePerDay = 0;
        carPricePerDay = NumberValidUtil.getInstance().doublePositiveNumberValid(carPricePerDay, LanguagePropertyLoader.getProperty("OCM_ENTER_CAR_PRICE"));
//        Стоимость заказа рассчитывается из указанной стоимости автомобиля за сутки и срока аренды
        double orderPrice = carPricePerDay * rentalPeriod;
        do {
            operationNumber = NumberValidUtil.getInstance().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("OCM_ORDER_CONFIRMATION"));
            switch (operationNumber) {
                case 1:
                    try {
                        order = new Order();
                        car = new Car();

                        car.setName(carName);
                        car.setState("WITHOUT_DAMAGE");
                        CarService.getInstance().addNewCar(car);
//                        Метод, который проверяет, есть ли клиент с введёнными данными в бд. Если такой клиент имеется, то мы не создаём новый объект клиента
                        client = searchClientInDb(clientName, clientAddress);


                        order.setPrice(orderPrice);
                        order.setState("Approved");
                        order.setDate(new Timestamp(new GregorianCalendar().getTimeInMillis()));
                        order.setTime(rentalPeriod);
                        order.setCar(car);
                        order.setClient(client);
                        OrderService.getInstance().addOrder(order);
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
                        CarService.getInstance().addNewCar(car);

                        //                        Метод, который проверяет, есть ли клиент с введёнными данными в бд. Если такой клиент имеется, то мы не создаём новый объект клиента
                        client = searchClientInDb(clientName, clientAddress);

                        order.setState("Declined");
                        order.setDate(new Timestamp(new GregorianCalendar().getTimeInMillis()));
                        order.setTime(rentalPeriod);
                        order.setCar(car);
                        order.setClient(client);
                        OrderService.getInstance().addOrder(order);
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

    /**
     * Метод, который проверяет, есть ли клиент с введёнными данными в бд. Если такой клиент имеется, то мы не создаём новый объект клиента
     *
     * @param clientName    - имя клиента
     * @param clientAddress - адрес клиента
     * @return возвращает либо клиента из бд, либо нового
     * @throws NoConnectionJDBCException
     * @see NoConnectionJDBCException
     */
    private Client searchClientInDb(String clientName, String clientAddress) throws NoConnectionJDBCException {
        Client client;
        final List<Client> collect = ClientService.getInstance().getAllClients().stream().filter(name -> name.getName().equals(clientName)).filter(address -> address.getAddress().equals(clientAddress)).collect(Collectors.toList());
        if (collect.size() == 0) {
            client = new Client();
            client.setName(clientName);
            client.setAddress(clientAddress);
            ClientService.getInstance().addClient(client);
        } else {
            client = collect.get(0);
        }
        return client;
    }
}
