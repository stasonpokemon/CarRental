package services;

import dao.ClientDaoImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pojo.Client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest extends TestCase {

    @Mock
    private ClientDaoImpl clientDao;
    @InjectMocks
    private ClientService clientService;

    @Test
    public void testAddClient() {
        Client client = mock(Client.class);
        clientService.addClient(client);
        verify(clientDao).create(client);
    }

    @Test
    public void testGetAllClients() {
        clientService.getAllClients();
        verify(clientDao).readAll();
    }
}