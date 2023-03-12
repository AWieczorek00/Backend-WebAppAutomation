package pl.backendbscthesis.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.backendbscthesis.Entity.*;
import pl.backendbscthesis.Service.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class OrderControllerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;


    @Test
    void getAllOrders() {
        //given
        List<Order> orderList = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();
        List<Activities> activities  = new ArrayList<>();
        List<Part> parts = new ArrayList<>();
        orderList.add(new Order(2l,new Client(),employeeList,activities,parts, LocalDate.now(),LocalDate.now().plusDays(10),3f,12f,"brak","test","test",""));
        given(orderService.findAllOrders()).willReturn(orderList);

        //when
        ResponseEntity<List<Order>> allOrders = orderController.getAllOrders();
        List<Order> result = allOrders.getBody();

        //then
        assertEquals(orderList,result);
        assertEquals(HttpStatus.OK,allOrders.getStatusCode());
    }

    @Test
    void createOrder() {
        //given
        List<Employee> employeeList = new ArrayList<>();
        List<Activities> activities  = new ArrayList<>();
        List<Part> parts = new ArrayList<>();
        Order order = new Order(2l, new Client(), employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(10), 3f, 12f, "brak", "test", "test", "");
        given(orderService.createNewOrder(order)).willReturn(order);

        //when
        ResponseEntity<Order> orderRE = orderController.createOrder(order);
        Order result = orderRE.getBody();

        //then
        assertEquals(order,result);
        assertEquals(HttpStatus.CREATED,orderRE.getStatusCode());
    }

    @Test
    void deleteOrder() {
        //given
        Long id = 2l;

        //when
        ResponseEntity<?> responseEntity = orderController.deleteOrder(id);

        //then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(orderService,times(1)).deleteOrderById(id);
    }

    @Test
    void createDuplicatedOrder() {
        //given
        List<Employee> employeeList = new ArrayList<>();
        List<Activities> activities  = new ArrayList<>();
        List<Part> parts = new ArrayList<>();
        Long id = 2l;
        Order duplicate = new Order(2l, new Client(), null, null, null, LocalDate.now(), null, 0, 0, "brak", "test", "test", "");
        Order order = new Order(2l, new Client(), employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(10), 3f, 12f, "brak", "test", "test", "");
        given(orderService.duplicateOrderById(id)).willReturn(duplicate);

        //when
        ResponseEntity<Order> duplicatedOrder = orderController.createDuplicatedOrder(id);
        Order result = duplicatedOrder.getBody();

        //then
        assertEquals(HttpStatus.CREATED,duplicatedOrder.getStatusCode());
        assertEquals(duplicate,result);
    }

    @Test
    void getOneOrder() {
        //given
        Long id = 2l;
        List<Employee> employeeList = new ArrayList<>();
        List<Activities> activities  = new ArrayList<>();
        List<Part> parts = new ArrayList<>();
        Order order = new Order(2l, new Client(), employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(10), 3f, 12f, "brak", "test", "test", "");
        given(orderService.findOrderById(id)).willReturn(order);

        //when
        ResponseEntity<Order> orderRE = orderController.getOneOrder(id);
        Order result = orderRE.getBody();

        //then
        assertEquals(HttpStatus.OK,orderRE.getStatusCode());
        assertEquals(order,result);
    }

    @Test
    void updateOrder() {
        //given
        Long id = 2L;
        List<Employee> employeeList = new ArrayList<>();
        List<Activities> activities  = new ArrayList<>();
        List<Part> parts = new ArrayList<>();
        Order order = new Order(2l, new Client(), employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(10), 3f, 12f, "brak", "test", "test", "");
        Order updatedOrder = new Order(2l, new Client(), employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(10), 3f, 12f, "brak", "test", "test", "");
        given(orderService.updateOrder(order)).willReturn(updatedOrder);

        //when
        ResponseEntity<Order> orderResponseEntity = orderController.updateOrder(order);
        Order result = orderResponseEntity.getBody();

        //then
        assertEquals(HttpStatus.OK,orderResponseEntity.getStatusCode());
        assertEquals(updatedOrder,result);
    }

    @Test
    void createOrderProtocol() {
    }

    @Test
    void createOrderInvoice() {
    }
}