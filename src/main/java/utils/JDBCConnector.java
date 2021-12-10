package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {

    // Используем шаблон одиночка, чтобы не плодить множество экземпляров класса JDBCConnector
    private static JDBCConnector instance = null;

    public static JDBCConnector getInstance() throws SQLException {
        if (instance == null) {
            instance = new JDBCConnector();
        }
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private final Connection connection;

    private JDBCConnector() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
//        DriverManager.registerDriver();

        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(JDBCPropertyLoader.getProperty("URL"),
                JDBCPropertyLoader.getProperty("USERNAME"),
                JDBCPropertyLoader.getProperty("PASSWORD"));
    }

    public Connection getConnection() {
        return connection;
    }

    public void connectionClose() throws SQLException {
        connection.close();
    }
}
