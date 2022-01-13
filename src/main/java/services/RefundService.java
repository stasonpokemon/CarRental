package services;

import dao.RefundDaoImpl;
import exceptions.NoConnectionJDBCException;
import pojo.Refund;

import java.sql.SQLException;

/**
 * Сервисный класс для возврата автомобиля со свойствами <b>instance</b> и <b>refundDaoImpl</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public class RefundService {

    /**
     * Статическое поле сервисного класса {@link RefundService} для реализации Singleton
     */
    private static RefundService instance;

    /**
     * Поле ссылки на объект {@link RefundDaoImpl}
     */
    private final RefundDaoImpl refundDaoImpl;


    /**
     * Статическая функция получения значения поля {@link RefundService#instance}
     *
     * @return возвращает экземпляр класса {@link RefundService}
     * @throws NoConnectionJDBCException - при неправильном подключении к бд
     */
    public static RefundService getInstance() throws NoConnectionJDBCException {
        if (instance == null) {
            instance = new RefundService();
        }
        return instance;
    }

    /**
     * Приватный конструктор - создание нового объекта в единственном экземпляре при помощи Singleton
     *
     * @throws NoConnectionJDBCException - при неправильном подключении к бд
     */
    private RefundService() throws NoConnectionJDBCException {
        try {
            refundDaoImpl = RefundDaoImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoConnectionJDBCException("Нет подключения к бд");
        }
    }


    /**
     * Функция добавление нового возврата автомобиля и получения его идентификатора {@link Refund#getId()}
     *
     * @param refund - объект добавляемого возврата автомобиля
     * @return возвращает идентификатор добавляемого возврата автомобиля
     */
    public Integer addNewRefund(Refund refund) {
        int maxRefundId = refundDaoImpl.getMaxRefundId();
        if (maxRefundId != 0) {
            refund.setId(maxRefundId + 1);
        } else {
            refund.setId(1);
        }
        refundDaoImpl.create(refund);
        return refund.getId();
    }

}
