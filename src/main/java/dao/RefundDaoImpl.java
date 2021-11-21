package dao;

import pojo.Refund;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RefundDaoImpl extends BaseDaoImpl implements RefundDao{

    private static RefundDaoImpl refundDao;

    protected RefundDaoImpl() throws SQLException {
    }

    public static RefundDaoImpl getRefundDao() {
        if (refundDao == null){
            try {
                refundDao = new RefundDaoImpl();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    public Refund read(Integer id) {
        return null;
    }

    @Override
    public void update(Refund refund) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Refund> readAll() {
        return null;
    }

    @Override
    public int getMaxRefundId() {
        String sql = "SELECT MAX(id) FROM refunds";
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
}
