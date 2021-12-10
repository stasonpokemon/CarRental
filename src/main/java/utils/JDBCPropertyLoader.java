package utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

public class JDBCPropertyLoader {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(LanguagePropertyLoader.class.getClassLoader().getResourceAsStream("db_config.properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey, "There is no key in the property file");
    }
}
