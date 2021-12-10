package utils;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

public class LanguagePropertyLoader {
    private final static Properties properties;

    static {
        properties = new Properties();
            selectRussianLanguageProperty();
    }


    public static void selectEnglishLanguageProperty() {
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(LanguagePropertyLoader.class.getClassLoader().getResourceAsStream("eng_language_config.properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void selectRussianLanguageProperty() {
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(LanguagePropertyLoader.class.getClassLoader().getResourceAsStream("rus_language_config.properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey, "There is no key in the property file");
    }
}
