package utils;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

/**
 * Util класс для подключения property файлов с информауией для интернационализации, со свойствами<b>properties</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class LanguagePropertyLoader {
    /**
     * Статическое Final поле properties
     */
    private final static Properties properties;

    /*
      Статический инициализатор для загрузки properties файлов до создания основного класса Main
     */
    static {
        properties = new Properties();
        selectRussianLanguageProperty();
    }

    /**
     * Функция загрузки properties файла с английским языком
     *
     * @throws IOException - возникает при ошибке загрузки файла
     */
    public static void selectEnglishLanguageProperty() {
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(LanguagePropertyLoader.class.getClassLoader().getResourceAsStream("eng_language_config.properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция загрузки properties файла с русским языком
     *
     * @throws IOException - возникает при ошибки загрузки файла
     */
    public static void selectRussianLanguageProperty() {
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(LanguagePropertyLoader.class.getClassLoader().getResourceAsStream("rus_language_config.properties"))));
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
