package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Util класс для соединения с базой данных, со свойствами <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class JDBCConnector {

    /**
     * Статическое поле connect класса {@link JDBCConnector} для реализации Singleton
     */
    private static JDBCConnector instance = null;

    /**
     * Статическая функция получения значения поля {@link JDBCConnector#instance}
     *
     * @return возвращает экземпляр класса {@link JDBCConnector}
     * @throws SQLException
     */
    public static JDBCConnector getInstance() throws SQLException {
        if (instance == null) {
            instance = new JDBCConnector();
        }
        return instance;
    }

    /**
     * Статическое поле объекта, в котором будет храниться соединение с БД {@link Connection}
     */
    private final Connection connection;

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     *
     * @throws SQLException - при неправильном подключении к бд
     */
    private JDBCConnector() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
//        DriverManager.registerDriver();

        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(JDBCPropertyLoader.getProperty("URL"),
                JDBCPropertyLoader.getProperty("USERNAME"),
                JDBCPropertyLoader.getProperty("PASSWORD"));
    }

    /**
     * Функция получения экземпляра класса {@link Connection}
     *
     * @return возвращает экземпляр класса Connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Функция закрытия соединения {@link Connection}
     *
     * @throws SQLException - при неправильном поключении к бд
     */
    public void connectionClose() throws SQLException {
        connection.close();
    }
}
