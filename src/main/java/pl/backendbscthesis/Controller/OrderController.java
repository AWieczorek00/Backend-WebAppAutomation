package pl.backendbscthesis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.backendbscthesis.Entity.Order;
import pl.backendbscthesis.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
@EnableTransactionManagement
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orderList = orderService.findAllOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Order> postOrder(@RequestBody Order orderBody){
        Order newOrder = orderService.add(orderBody);
        return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/duplicate")
    public ResponseEntity<Order> duplicateOrder(@RequestBody Long id){
        Order duplicateOrder = orderService.duplicate(id);
        return new ResponseEntity<>(duplicateOrder,HttpStatus.CREATED);
    }


    @GetMapping("/one/{id}")
    public ResponseEntity<Order> getOneOrder(@PathVariable Long id){
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

//    @PutMapping("/update")
//    public ResponseEntity<Order> putOrder(@RequestBody Long id)



}
