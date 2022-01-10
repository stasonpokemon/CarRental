package utils;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

/**
 * Util класс для подключения property файла с информауией для соединения с базой данных, со свойствами<b>properties</b>.
 *
 * @version 1.1
 * @autor Stanislav Trebnikov
 */
public class JDBCPropertyLoader {
    /**
     * Статическое Final поле properties
     */
    private final static Properties properties;

    /*
      Статический инициализатор для загрузки properties файлов до создания основного класса Main
     */
    static {
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(LanguagePropertyLoader.class.getClassLoader().getResourceAsStream("db_config.properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция получения значения properties по ключу
     *
     * @param propertyKey - ключ
     * @return возвращает значение properties
     */
    public static String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey, "There is no key in the property file");
    }
}
