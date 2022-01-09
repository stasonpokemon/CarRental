package dao;

import pojo.Refund;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RefundDaoImpl extends BaseDaoImpl implements RefundDao {

    private static RefundDaoImpl refundDao;

    private RefundDaoImpl() throws SQLException {
    }

    public static RefundDaoImpl getInstance() throws SQLException {
        if (refundDao == null) {
                refundDao = new RefundDaoImpl();
        }
        return refundDao;
    }

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

    @Override
    public int getMaxRefundId() {
        String sql = "SELECT MAX(id) as 'max' FROM refunds";
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







}
