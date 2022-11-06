package pl.backendbscthesis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.backendbscthesis.Entity.Client;
import pl.backendbscthesis.service.ClientService;


import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins="*")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clientList = clientService.findAllClient();
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Client> postClient(@RequestBody Client clientBody){
//        Client client = clientService.createClient(clientBody);
//        return new ResponseEntity<>(client,HttpStatus.CREATED);
//    }



}
