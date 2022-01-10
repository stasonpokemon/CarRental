package pojo;

/**
 * Класс клиента наследуемый от класса {@link Entity}, со свойствами <b>name</b> и <b>address</b>.
 *
 * @version 1.1
 * @autor Stanislav Trebnikov
 */
public class Client extends Entity {
    /**
     * Поле ФИО клиента
     */
    private String name;
    /**
     * Поле адреса клиента
     */
    private String address;

    /**
     * Функция получения значения поля {@link Client#name}
     *
     * @return возвращает ФИО клиента
     */
    public String getName() {
        return name;
    }

    /**
     * Процедура определения ФИО клиента {@link Client#name}
     *
     * @param name - ФИО клиента
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция получения значения поля {@link Client#address}
     *
     * @return возвращает адрес клиента
     */
    public String getAddress() {
        return address;
    }

    /**
     * Процедура определения адреса клиента {@link Client#address}
     *
     * @param address - адрес клиента
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Метод toString
     *
     * @return возвращает информацию об объекте клиента в виде строки
     * */
    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
