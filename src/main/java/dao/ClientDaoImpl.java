package dao;

import pojo.Car;
import pojo.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends BaseDaoImpl implements ClientDao {
    private static ClientDaoImpl clientDao;

    private ClientDaoImpl() throws SQLException {
    }

    public static ClientDaoImpl getInstance() throws SQLException {
        if (clientDao == null) {
                clientDao = new ClientDaoImpl();
        }
        return clientDao;
    }

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

    @Override
    public int getMaxClientId() {
        String sql = "SELECT MAX(id) FROM clients";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

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
            while (resultSet.next()){
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
