package dao;

import pojo.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO класс для соединения сущности {@link Client} и базы данных
 * наследуемый от класса {@link BaseDaoImpl} и реализующий интерфейс {@link ClientDao}
 * со свойствами <b>instance</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class ClientDaoImpl extends BaseDaoImpl implements ClientDao {

    /**
     * Статическое поле DAO класса {@link ClientDaoImpl} для реализации Singleton
     */
    private static ClientDaoImpl instance;

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     *
     * @throws SQLException
     */
    private ClientDaoImpl() throws SQLException {
    }

    /**
     * Статическая функция получения значения поля {@link ClientDaoImpl#instance}
     *
     * @return возвращает экземпляр класса {@link ClientDaoImpl}
     * @throws SQLException
     */
    public static ClientDaoImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new ClientDaoImpl();
        }
        return instance;
    }

    /**
     * Функция создания(добавления) нового клиента в таблицу clients
     *
     * @return возвращает id созданного клиента
     */
    @Override
    public Integer create(Client client) {
        String sql = "INSERT INTO clients (id, name , address) VALUES(?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, client.getId());
            statement.setString(2, client.getName());
            statement.setString(3, client.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client.getId();
    }

    /**
     * Функция получения максимального значения id в таблице clients
     *
     * @return возвращает максимальное значение id
     */
    @Override
    public int getMaxClientId() {
        String sql = "SELECT MAX(id) as 'max' FROM clients";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
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

    /**
     * Функция получения всех клиентов из таблицы clients
     *
     * @return возвращает коллекцию клиентов
     */
    @Override
    public List<Client> readAll() {
        String sql = "SELECT id, name, address FROM clients";
        PreparedStatement statement;
        ResultSet resultSet;
        List<Client> clients = new ArrayList<>();
        Client client;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setAddress(resultSet.getString("address"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
