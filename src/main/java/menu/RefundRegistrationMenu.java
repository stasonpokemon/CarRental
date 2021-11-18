package menu;

public class RefundRegistrationMenu {
    private static RefundRegistrationMenu menu;

    public static RefundRegistrationMenu getMenu() {
        if (menu == null){
            menu = new RefundRegistrationMenu();
        }
        return menu;
    }

    public void refundRegistration(){

    }
}
