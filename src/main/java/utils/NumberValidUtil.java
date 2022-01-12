package utils;


import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Util класс для валидации числовых значений, со свойствами <b>scanner</b> и <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class NumberValidUtil {

    /**
     * Поле сканера для чтения строк из консоли
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * Статическое поле класса {@link NumberValidUtil} для реализации Singleton
     */
    private static NumberValidUtil instance;

    /**
     * Статическая функция получения значения поля {@link NumberValidUtil#instance}
     *
     * @return возвращает экземпляр класса {@link NumberValidUtil}
     */
    public static NumberValidUtil getInstance() {
        if (instance == null) {
            instance = new NumberValidUtil();
        }
        return instance;
    }

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    private NumberValidUtil() {
    }

    /**
     * Функция для проверки данных типа int
     *
     * @param number   - значение, над которым мы хотим провести валидацию
     * @param textInfo - текстовое сообщение
     * @return возвращает значение, над которым мы провели валидацию
     * @throws InputMismatchException
     */
    public int intNumberValid(int number, String textInfo) {
        boolean numberValid = false;
        do {
            try {
                System.out.println(textInfo);
                number = scanner.nextInt();
                scanner.nextLine();
                numberValid = true;
            } catch (InputMismatchException e) {
                System.out.println(LanguagePropertyLoader.getProperty("NVU_ENTER_INTEGER"));
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!numberValid);
        return number;
    }


    /**
     * Функция для проверки положительных данных типа int
     *
     * @param number   - значение, над которым мы хотим провести валидацию
     * @param textInfo - текстовое сообщение
     * @return возвращает значение, над которым мы провели валидацию
     * @throws InputMismatchException
     */
    public int intPositiveNumberValid(int number, String textInfo) {
        boolean numberValid = false;
        do {
            try {
                System.out.println(textInfo);
                number = scanner.nextInt();
                scanner.nextLine();
                if (number < 0) {
                    System.out.println(LanguagePropertyLoader.getProperty("NVU_ENTER_POSITIVE_INTEGER"));
                } else {
                    numberValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println(LanguagePropertyLoader.getProperty("NVU_ENTER_POSITIVE_INTEGER"));
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!numberValid);
        return number;
    }


    /**
     * Функция для проверки данных типа double
     *
     * @param number   - значение, над которым мы хотим провести валидацию
     * @param textInfo - текстовое сообщение
     * @return возвращает значение, над которым мы провели валидацию
     * @throws InputMismatchException
     */
    public double doubleNumberValid(double number, String textInfo) {
        boolean numberValid = false;
        do {
            try {
                System.out.println(textInfo);
                number = scanner.nextDouble();
                scanner.nextLine();
                numberValid = true;
            } catch (InputMismatchException e) {
                System.out.println(LanguagePropertyLoader.getProperty("NVU_ENTER_DOUBLE"));
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!numberValid);
        return number;
    }


    /**
     * Функция для проверки положительных данных типа double
     *
     * @param number   - значение, над которым мы хотим провести валидацию
     * @param textInfo - текстовое сообщение
     * @return возвращает значение, над которым мы провели валидацию
     * @throws InputMismatchException
     */
    public double doublePositiveNumberValid(double number, String textInfo) {
        boolean numberValid = false;
        do {
            try {
                System.out.println(textInfo);
                number = scanner.nextDouble();
                scanner.nextLine();
                if (number < 0) {
                    System.out.println(LanguagePropertyLoader.getProperty("NVU_ENTER_POSITIVE_DOUBLE"));
                } else {
                    numberValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println(LanguagePropertyLoader.getProperty("NVU_ENTER_POSITIVE_DOUBLE"));
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!numberValid);
        return number;
    }
}
