package pojo;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Класс заказа, наследуемый от класса {@link Entity}, со свойствами <b>price</b>, <b>state</b>, <b>date</b>, <b>time</b>, <b>car</b>, <b>client</b> и <b>refund</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class Order extends Entity {
    /**
     * Поле стоимости заказа
     */
    private double price;
    /**
     * Поле статуса заказа
     */
    private String state;
    /**
     * Поле даты заказа
     */
    private Timestamp date;
    /**
     * Поле срока аренды автомобиля
     */
    private int time;
    /**
     * Поле заказанного автомобиля
     */
    private Car car;
    /**
     * Поле клиента, который сделал заказ
     */
    private Client client;
    /**
     * Поле возврата автомобиля
     */
    @SerializedName("returned")
    private Refund refund;

    /**
     * Функция получения значения поля {@link Order#price}
     *
     * @return возвращает стоимость заказа автомобиля
     */
    public double getPrice() {
        return price;
    }

    /**
     * Процедура определения стоимости заказа автомобиля {@link Order#price}
     *
     * @param price - стоимость заказа автомобиля
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Функция получения значения поля {@link Order#state}
     *
     * @return возвращает статус заказа автомобиля
     */
    public String getState() {
        return state;
    }

    /**
     * Процедура определения статуса заказа автомобиля {@link Order#state}
     *
     * @param state - статус заказа автомобиля
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Функция получения значения поля {@link Order#date}
     *
     * @return возвращает дату заказа автомобиля
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * Процедура определения даты заказа автомобиля {@link Order#date}
     *
     * @param date - дата заказа автомобиля
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * Функция получения значения поля {@link Order#time}
     *
     * @return возвращает срок аренды автомобиля
     */
    public int getTime() {
        return time;
    }

    /**
     * Процедура определения срока аренды автомобиля {@link Order#price}
     *
     * @param time - срок аренды автомобиля
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Функция получения значения поля {@link Order#car}
     *
     * @return возвращает объект заказанного автомобиля
     */
    public Car getCar() {
        return car;
    }

    /**
     * Процедура определения объекта заказанного автомобиля {@link Order#car}
     *
     * @param car - объект заказанного автомобиля
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Функция получения значения поля {@link Order#client}
     *
     * @return возвращает объект клиента сделавшего заказ автомобиля
     */
    public Client getClient() {
        return client;
    }

    /**
     * Процедура определения объекта клиента сделавшего заказ автомобиля {@link Order#client}
     *
     * @param client - объект клиента сделавшего заказ автомобиля
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Функция получения значения поля {@link Order#refund}
     *
     * @return возвращает объект возврата автомобиля
     */
    public Refund getRefund() {
        return refund;
    }

    /**
     * Процедура определения объекта возврата автомобиля {@link Order#refund}
     *
     * @param refund - объект возврата автомобиля
     */
    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    /**
     * Метод toString
     *
     * @return возвращает информацию об объекте заказа в виде строки
     * */
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
        return "Order{" +
                "price=" + price +
                ", state='" + state + '\'' +
                ", date=" + format.format(date) +
                ", time=" + time +
                ", car=" + car +
                ", client=" + client +
                ", refund=" + refund +
                '}';
    }
}
