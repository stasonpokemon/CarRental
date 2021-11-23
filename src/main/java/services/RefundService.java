package services;

import dao.RefundDaoImpl;
import pojo.Refund;

import java.sql.SQLException;

public class RefundService {

    private static RefundService service;

    public static RefundService getInstance() {
        if (service == null){
            service = new RefundService();
        }
        return service;
    }


    /*
     * Создать возврат
     * */
    public Integer addNewRefund(Refund refund) {
        try {
            int maxRefundId = RefundDaoImpl.getInstance().getMaxRefundId();
            if (maxRefundId != 0) {
                refund.setId(maxRefundId + 1);
            } else {
                refund.setId(1);
            }
            RefundDaoImpl.getInstance().create(refund);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return refund.getId();
    }

}
