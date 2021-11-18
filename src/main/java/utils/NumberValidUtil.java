package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberValidUtil {

    private static final String ENTER_INTEGER = "Не верный формат значения. Введите значение типа Integer";
    private static final String ENTER_DOUBLE = "Не верный формат значения. Введите значение типа Double";

    private final Scanner scanner = new Scanner(System.in);
    private static NumberValidUtil numberValidUtil;

    public static NumberValidUtil getOperationNumberUtil() {
        if (numberValidUtil == null) {
            numberValidUtil = new NumberValidUtil();
        }
        return numberValidUtil;
    }

    /*
     * Метод для проверки данных типа int
     * */
    public int intNumberValid(int number, String textInfo) {
        boolean numberValid = false;
        do {
            try {
                System.out.println(textInfo);
                number = scanner.nextInt();
                scanner.nextLine();
                numberValid = true;
            } catch (InputMismatchException e) {
                System.out.println(ENTER_INTEGER);
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!numberValid);
        return number;
    }

    /*
     * Метод для проверки данных типа double
     * */
    public double doubleNumberValid(double number, String textInfo) {
        boolean numberValid = false;
        do {
            try {
                System.out.println(textInfo);
                number = scanner.nextDouble();
                scanner.nextLine();
                numberValid = true;
            } catch (InputMismatchException e) {
                System.out.println(ENTER_DOUBLE);
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!numberValid);
        return number;
    }
}
