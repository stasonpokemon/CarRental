package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberValidUtil {

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
                System.out.println(LanguagePropertyLoader.getProperty("NVU_ENTER_INTEGER"));
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!numberValid);
        return number;
    }

    /*
     * Метод для проверки положительных данных типа int
     * */
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
                System.out.println(LanguagePropertyLoader.getProperty("NVU_ENTER_DOUBLE"));
                scanner.nextLine();
                System.out.println("Exception: " + e);
            }
        } while (!numberValid);
        return number;
    }

    /*
     * Метод для проверки положительных данных типа double
     * */
    public double doublePositiveNumberValid(double number, String textInfo) {
        boolean numberValid = false;
        do {
            try {
                System.out.println(textInfo);
                number = scanner.nextDouble();
                scanner.nextLine();
                if (number < 0) {
                    System.out.println(LanguagePropertyLoader.getProperty("NVU_ENTER_POSITIVE_DOUBLE"));
                }else {
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
