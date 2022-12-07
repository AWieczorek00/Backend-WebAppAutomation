package pl.backendbscthesis.Controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.backendbscthesis.Entity.Order;
import pl.backendbscthesis.PdfGeneration;
import pl.backendbscthesis.service.OrderService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
@EnableTransactionManagement
public class OrderController {
    private final OrderService orderService;




    @Autowired
    public OrderController(OrderService orderService) throws DocumentException, IOException {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderList = orderService.findAllOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Order> postOrder(@RequestBody Order orderBody) {
        Order newOrder = orderService.add(orderBody);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/duplicate")
    public ResponseEntity<Order> duplicateOrder(@RequestBody Long id) {
        Order duplicateOrder = orderService.duplicate(id);
        return new ResponseEntity<>(duplicateOrder, HttpStatus.CREATED);
    }


    @GetMapping("/one/{id}")
    public ResponseEntity<Order> getOneOrder(@PathVariable Long id) {
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Order> putOrder(@RequestBody Order orderBody) {
        Order order = orderService.updateOrder(orderBody);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping(value = "/protocol", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> orderGeneration(@RequestBody Order orderBody) throws DocumentException, IOException {
        PdfGeneration pdfGeneration = new PdfGeneration();
        ByteArrayInputStream orderPdf = pdfGeneration.createFacture(orderBody);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(orderPdf));
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> test() {

        ByteArrayInputStream bis = PdfGeneration.test();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}
