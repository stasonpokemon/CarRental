package dao;

import pojo.Refund;

public interface RefundDao extends Dao<Refund> {
    int getMaxRefundId();
}
