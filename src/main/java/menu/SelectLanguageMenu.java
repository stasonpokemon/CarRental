package menu;

import utils.LanguagePropertyLoader;
import utils.NumberValidUtil;

public class SelectLanguageMenu implements Menu {
    private static SelectLanguageMenu instance;
    private static int operationNumber;

    private SelectLanguageMenu() {
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new SelectLanguageMenu();
        }
        return instance;
    }

    @Override
    public void getMenu() {
        boolean exit = false;
        do {
            operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber, LanguagePropertyLoader.getProperty("SLM_SELECT_LANGUAGE"));
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
