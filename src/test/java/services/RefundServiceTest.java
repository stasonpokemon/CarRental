package services;

import dao.RefundDaoImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pojo.Refund;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)

public class RefundServiceTest extends TestCase {

    @Mock
    private RefundDaoImpl refundDao;
    @InjectMocks
    private RefundService refundService;

    @Test
    public void testAddNewRefund() {
        Refund refund = mock(Refund.class);
        refundService.addNewRefund(refund);
        verify(refundDao).create(refund);
    }

}