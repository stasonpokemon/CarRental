package menu;

public class OrderCreationMenu {
    private static OrderCreationMenu menu;

    public static OrderCreationMenu getMenu() {
        if (menu == null){
            menu = new OrderCreationMenu();
        }
        return menu;
    }

    public void creatingAnOrder(){
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
    }

}
