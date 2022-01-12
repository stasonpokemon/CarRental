package dao;

import utils.JDBCConnector;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Абстрактный класс, который передаёт классам наследникам соединение JDBCConnector, со свойствами <b>jdbcConnector</b> и <b>connection</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
abstract public class BaseDaoImpl {

    /**
     * Поле экземпляра класса {@link JDBCConnector}
     */
    private final JDBCConnector jdbcConnector = JDBCConnector.getInstance();
    /**
     * Поле соединения JDBCConnector
     */
    protected Connection connection = jdbcConnector.getConnection();

    /**
     * Конструктор
     *
     * @throws SQLException - при неправильном подключении к бд
     */
    protected BaseDaoImpl() throws SQLException {
    }
}
