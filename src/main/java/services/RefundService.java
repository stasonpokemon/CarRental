package services;

import dao.RefundDaoImpl;
import pojo.Refund;

import java.sql.SQLException;

/**
 * Сервесный класс для возврата автомобиля со свойствами <b>instance</b>.
 *
 * @version 1.1
 * @autor Stanislav Trebnikov
 */
public class RefundService {

    /**
     * Статическое поле сервесного класса {@link RefundService} для реализации Singleton
     */
    private static RefundService instance;

    /**
     * Статическая функция получения значения поля {@link RefundService#instance}
     *
     * @return возвращает экземпляр класса {@link RefundService}
     */
    public static RefundService getInstance() {
        if (instance == null) {
            instance = new RefundService();
        }
        return instance;
    }

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     */
    private RefundService() {
    }


    /**
     * Функция добавление нового возврата автомобиля и получения его идентификатора {@link Refund#getId()}
     *
     * @param refund - объект добавляемого возврата автомобиля
     * @return возвращает идентификатор добавляемого возврата автомобиля
     * @throws SQLException - при неправильном поключении к бд
     */
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
