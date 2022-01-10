package pojo;

import com.google.gson.annotations.Expose;

/**
 * Класс возврата, наследуемый от класса {@link Entity}, со свойствами <b>state</b>, <b>detail</b> и <b>price</b>.
 *
 * @version 1.1
 * @autor Stanislav Trebnikov
 */
public class Refund extends Entity {
    /**
     * Поле статуса возврата автомобиля
     */
    @Expose(serialize = true)
    private String state;
    /**
     * Поле описания повеждённых деталей автомобиля
     */
    @Expose(serialize = true)
    private String detail;
    /**
     * Поле стоимости возврата автомобиля
     */
    @Expose(serialize = true)
    private double price;

    /**
     * Функция получения значения поля {@link Refund#state}
     *
     * @return возвращает статус возврата автомобиля
     */
    public String getState() {
        return state;
    }

    /**
     * Процедура определения статуса возврата автомобиля {@link Refund#state}
     *
     * @param state - статус возврата автомобиля
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Функция получения значения поля {@link Refund#detail}
     *
     * @return возвращает строку повеждённых деталей автомобиля
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Процедура определения повеждённых деталей автомобиля {@link Refund#detail}
     *
     * @param detail - строка повеждённых деталей автомобиля
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Функция получения значения поля {@link Refund#price}
     *
     * @return возвращает стоимость возврата автомобиля
     */
    public double getPrice() {
        return price;
    }

    /**
     * Процедура определения стоимости возврата автомобиля {@link Refund#price}
     *
     * @param price - стоимость возврата автомобиля
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Метод toString
     *
     * @return возвращает информацию об объекте возврата автомобиля в виде строки
     */
    @Override
    public String toString() {
        return "Refund{" +
                "state='" + state + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                '}';
    }
}
