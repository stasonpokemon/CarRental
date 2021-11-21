package menu;

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

public class OrderCreationMenu {
    public static final String ORDER_CONFIRMATION = "Подтверждение заказа:\n" +
            "1. Одобрить\n" +
            "2. Отклонить";

    public static final String ORDER_APPROVED = "Заказ принят...";
    public static final String ORDER_DECLINED = "Заказ отклонён...";
    private static final String NO_OPERATION = "Не существует введённой вами операции, попробуйте ещё раз...";



    private static OrderCreationMenu menu;
    private final Scanner scanner = new Scanner(System.in);
    private int operationNumber;

    public static OrderCreationMenu getMenu() {
        if (menu == null) {
            menu = new OrderCreationMenu();
        }
        return menu;
    }

    public void creatingAnOrder() {
        /*
         * 1. Выбор авто из списка доступных (Введите авто, которое выбрал клиент)
         * 2. Клиент указывает Имя, Фамилию и адрес (Введите данные клиента)
         * 3. Клиент указывает срок аренды (Введите срок аренды автомобиля)
         * 4. (Введите стоимость автомобиля за сутки) или (Ввеите стоимость заказа)
         * 5. (Подтвердить / Отколнить)
         *
         * -------- информация о заказе
         * (1. Вернуться назад)
         * */

        boolean exit = false;
        Order order = new Order();
        Car car = new Car();
        Client client = new Client();


//        -Клиент выбирает автомобиль
        System.out.println("Укажите название выбранного автомобиля:");
        String carName = scanner.nextLine();
//        -Клиент называет имя, фамилию и адрес
        System.out.println("Укажите имя и фамилию клиента:");
        String clientName = scanner.nextLine();
        System.out.println("Укажите адрес клиента:");
        String clientAddress = scanner.nextLine();
//        -Клиент указывает срок аренды
        System.out.println("Введите срок арнеды:");
        int rentalPeriod = scanner.nextInt();
        System.out.println("Введите стоимость автомобиля за сутки:");
        double carPricePerDay = scanner.nextDouble();
//        Стоимость заказа рассчитывается из указанной стоимости автомобиля за сутки и срока аренды
        double orderPrice = carPricePerDay * rentalPeriod;


        do {
            operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, ORDER_CONFIRMATION);
            switch (operationNumber) {
                case 1:
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
                    exit = true;
                    System.out.println(ORDER_APPROVED);
                    break;
                case 2:
                    car.setName(carName);
                    car.setState("WITHOUT_DAMAGE");
                    CarService.getService().addNewCar(car);

                    client.setName(clientName);
                    client.setAddress(clientAddress);
                    ClientService.getService().addClient(client);

//                    Цену не указываем, потому что в json файле не требуется
//                    order.setPrice(orderPrice);
                    order.setState("Declined");
                    order.setDate(new Timestamp(new GregorianCalendar().getTimeInMillis()));
                    order.setTime(rentalPeriod);
                    order.setCar(car);
                    order.setClient(client);
                    OrderService.getOrderService().addOrder(order);
                    exit = true;
                    System.out.println(ORDER_DECLINED);
                    break;
                default:
                    System.out.println(NO_OPERATION);
                    break;
            }
        } while (!exit);


    }

}
