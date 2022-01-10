package menu.jsonParseMenu;

import com.google.gson.GsonBuilder;
import exceptions.NoConnectionJDBCException;
import menu.Menu;
import pojo.Car;
import services.CarService;
import utils.LanguagePropertyLoader;

import java.util.List;
import java.util.Scanner;

public class SerializeCarsToJsonMenu extends SerializeToJson implements Menu {
    private final Scanner scanner = new Scanner(System.in);


    private static SerializeCarsToJsonMenu instance;

    public static SerializeCarsToJsonMenu getInstance() {
        if (instance == null) {
            instance = new SerializeCarsToJsonMenu();
        }
        return instance;
    }

    private SerializeCarsToJsonMenu() {

    }

    @Override
    public void getMenu() {
        try {
            System.out.println(LanguagePropertyLoader.getProperty("SCATJM_ENTER_PATH"));
            String path = scanner.next();
            gson = new GsonBuilder().setPrettyPrinting().create();
            propName = "cars";
            final List<Car> allCars = CarService.getInstance().getAllCars();
            serialize(allCars, path);
        } catch (NoConnectionJDBCException e) {
            e.printStackTrace();
        }
    }
}
