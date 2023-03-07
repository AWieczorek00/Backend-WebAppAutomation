package pl.backendbscthesis.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.backendbscthesis.Entity.Client;
import pl.backendbscthesis.Service.ClientService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    void getAllClients() {
        //given
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(0L,"Sklep u Romka","123-456-10-10","Jana Pawła","Tuliszków","62-700","3","2A","123456789","uromka@wp.pl","firma"));
        clientList.add(new Client(0L,"Promont","987-654-10-10","Focus","Bydgoszcz","62-800","41","","987654321","poromont@onet.pl","firma"));
        given(clientService.findAllClients()).willReturn(clientList);

        //when
        ResponseEntity<List<Client>> allClients = clientController.getAllClients();
        List<Client> result = allClients.getBody();

        //then
        assertEquals(HttpStatus.OK,allClients.getStatusCode());
        assertEquals(clientList,result);
    }
}
