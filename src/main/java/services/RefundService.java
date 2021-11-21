package services;

import dao.RefundDaoImpl;
import pojo.Refund;

public class RefundService {

    private static RefundService service;

    public static RefundService getService() {
        if (service == null){
            service = new RefundService();
        }
        return service;
    }


    /*
     * Создать возврат
     * */
    public Integer addNewRefund(Refund refund) {
        int maxRefundId = RefundDaoImpl.getRefundDao().getMaxRefundId();
        if (maxRefundId != 0) {
            refund.setId(maxRefundId + 1);
        } else {
            refund.setId(1);
        }
        RefundDaoImpl.getRefundDao().create(refund);
        return refund.getId();
    }

}
