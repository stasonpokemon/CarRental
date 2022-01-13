package services;

import dao.OrderDaoImpl;
import exceptions.NoConnectionJDBCException;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pojo.Order;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest extends TestCase {
    @Mock
    private OrderDaoImpl orderDao;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void testAddOrder() throws NoConnectionJDBCException, SQLException {
        Order order = mock(Order.class);
        orderService.addOrder(order);
        verify(orderDao).create(order);
    }

    @Test
    public void testFindAllOrders() {
        orderService.findAllOrders();
        verify(orderDao).readAll();
    }

    @Test
    public void testFindApprovedOrdersWithoutRefund() {
        orderService.findApprovedOrdersWithoutRefund();
        verify(orderDao).readApprovedOrdersWithoutRefund();
    }

    @Test
    public void testUpdate() {
        Order order = mock(Order.class);
        orderService.update(order);
        verify(orderDao).update(order);
    }
}