package pojo;

/**
 * Класс автомобиля наследуемый от класса {@link Entity}, со свойствами <b>name</b> и <b>state</b>.
 *
 * @version 1.1
 * @autor Stanislav Trebnikov
 */
public class Car extends Entity {
    /**
     * Поле названия автомобиля
     */
    private String name;
    /**
     * Поле статуса автомобиля
     */
    private String state;

    /**
     * Функция получения значения поля {@link Car#name}
     *
     * @return возвращает название автомобиля
     */
    public String getName() {
        return name;
    }

    /**
     * Процедура определения названия автомобиля {@link Car#name}
     *
     * @param name - название автомобиля
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция получения значения поля {@link Car#state}
     *
     * @return возвращает статус автомобиля
     */
    public String getState() {
        return state;
    }

    /**
     * Процедура определения статуса автомобиля {@link Car#state}
     *
     * @param state - статус автомобиля
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Метод toString
     *
     * @return возвращает информацию об объекте автомобиля в виде строки
     * */
    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
