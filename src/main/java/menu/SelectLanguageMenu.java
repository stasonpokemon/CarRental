package menu;

import utils.LanguagePropertyLoader;
import utils.NumberValidUtil;

/**
 * Класс меню, который позволяет выбрать язык приложения, реализующий интерфейс {@link Menu}, со свойствами <b>operationNumber</b> и <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class SelectLanguageMenu implements Menu {

    /**
     * Поле номера выбора операции
     */
    private int operationNumber;

    /**
     * Статическое поле класса меню {@link SelectLanguageMenu} для реализации Singleton
     */
    private static SelectLanguageMenu instance;

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    private SelectLanguageMenu() {
    }

    /**
     * Статическая функция получения значения поля {@link SelectLanguageMenu#instance}
     *
     * @return возвращает экземпляр класса {@link SelectLanguageMenu}
     */
    public static SelectLanguageMenu getInstance() {
        if (instance == null) {
            instance = new SelectLanguageMenu();
        }
        return instance;
    }

    /**
     * Функция вызова меню, которое позволяет выбрать язык приложения
     */
    @Override
    public void getMenu() {
        boolean exit = false;
        do {
            operationNumber = NumberValidUtil.getInstance().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("SLM_SELECT_LANGUAGE"));
            switch (operationNumber) {
                case 1:
                    LanguagePropertyLoader.selectRussianLanguageProperty();
                    exit = true;
                    break;
                case 2:
                    LanguagePropertyLoader.selectEnglishLanguageProperty();
                    exit = true;
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println(LanguagePropertyLoader.getProperty("SLM_NO_OPERATION"));
            }
        } while (!exit);
    }
}
