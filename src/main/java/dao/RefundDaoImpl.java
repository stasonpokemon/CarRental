package dao;

import pojo.Refund;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO класс для соединения сущности {@link Refund} и базы данных
 * наследуемый от класса {@link BaseDaoImpl} и реализующий интерфейс {@link RefundDao}
 * со свойствами <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class RefundDaoImpl extends BaseDaoImpl implements RefundDao {

    /**
     * Статическое поле DAO класса {@link RefundDaoImpl} для реализации Singleton
     */
    private static RefundDaoImpl instance;

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     *
     * @throws SQLException
     */
    private RefundDaoImpl() throws SQLException {
    }

    /**
     * Статическая функция получения значения поля {@link RefundDaoImpl#instance}
     *
     * @return возвращает экземпляр класса {@link RefundDaoImpl}
     * @throws SQLException
     */
    public static RefundDaoImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new RefundDaoImpl();
        }
        return instance;
    }

    /**
     * Функция создания(добавления) нового возврата в таблицу refunds
     *
     * @return возвращает id созданного возврата
     */
    @Override
    public Integer create(Refund refund) {
        String sql = "INSERT INTO refunds (id, state, price, detail)  VALUES(?, ?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, refund.getId());
            statement.setString(2, refund.getState());
            statement.setDouble(3, refund.getPrice());
            statement.setString(4, refund.getDetail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Функция получения максимального значения id в таблице refund
     *
     * @return возвращает максимальное значение id
     */
    @Override
    public int getMaxRefundId() {
        String sql = "SELECT MAX(id) as 'max' FROM refunds";
        PreparedStatement statement;
        ResultSet resultSet;
        int id = 0;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("max");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


}
