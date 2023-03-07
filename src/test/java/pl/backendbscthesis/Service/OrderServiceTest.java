package pl.backendbscthesis.Service;

import org.junit.jupiter.api.Test;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import pl.backendbscthesis.Entity.*;
import pl.backendbscthesis.Entity.template.ActivitiesTemplate;
import pl.backendbscthesis.Entity.template.PartsTemplate;
import pl.backendbscthesis.Repository.OrderRepository;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private  ActivitiesService activitiesService;
    @Mock
    private  PartService partService;
    @Mock
    private  ClientService clientService;
    @InjectMocks
    private OrderService orderService;


    @Test
    void findAllOrders() {
        //given
        List<Order> orderList = new ArrayList<>();
        Client promont = new Client(0L,"Promont","987-654-10-10","Focus","Bydgoszcz","62-800","41","","987654321","poromont@onet.pl","firma");
        Employee Adam = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        Employee Paulina = new Employee(5678L, "Paulina", "", "Żelek", "awieczorek0000@gmail.com", 123456789L, LocalDate.now());
        List<Employee> employeeList = Arrays.asList(Adam, Paulina);
        List<Activities> activities  = new ArrayList<>();
        Part oring = new Part(0L,"Oring",3.25f,0.23f,1);
        Part wezyk = new Part(0L,"Gumowy wąż",5,0.20f,1);
        List<Part> parts = Arrays.asList(oring, wezyk);
        orderList.add(new Order(2l,promont,employeeList,activities,parts,LocalDate.now(),LocalDate.now().plusDays(10),3f,12f,"brak","test","test",""));
        given(orderRepository.findAll()).willReturn(orderList);

        //when
        List<Order> result = orderService.findAllOrders();

        //then
        assertThat(result).isEqualTo(orderList);

    }

    @Test
    void createNewOrder() {
        //given
        List<Activities> activities  = Collections.emptyList();
        List<Part> parts = Collections.emptyList();
        List<Employee> employeeList = Collections.emptyList();
        Client promont = new Client();
        Order order = new Order(2l, promont, employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(10), 3f, 12f, "brak", "test", "test", "");
        given(orderRepository.saveAndFlush(order)).willReturn(order);

        //when
        Order result = orderService.createNewOrder(order);

        //that
        verify(activitiesService, times(1)).createAllActivitiesFromList(activities);
        verify(partService, times(1)).createAllParts(parts);
        verify(clientService, times(1)).createClient(promont);
        verify(orderRepository, times(1)).saveAndFlush(any(Order.class));

        assertEquals(order,result);

    }

    @Test
    void deleteOrderById() {
        //given
        Long id = 2L;

        //when
        orderService.deleteOrderById(id);

        //then
        verify(orderRepository).deleteById(id);
    }

    @Test
    void duplicateOrderById() {
        //given
        List<Activities> activities  = Collections.emptyList();
        List<Part> parts = Collections.emptyList();
        List<Employee> employeeList = Collections.emptyList();
        Client promont = new Client();
        Long id = 2L;
        Order order = new Order(2l, promont, employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(10), 3f, 12f, "brak", "test", "test", "");
        Order duplicate = new Order(2l, promont, null, null, null, LocalDate.now(), null, 0, 0, "brak", "test", "test", "");
        given(orderRepository.findById(id)).willReturn(Optional.of(order));
        given(orderRepository.save(any(Order.class))).willReturn(duplicate);

        //when
        Order duplicateOrderById = orderService.duplicateOrderById(2L);

        //then
        verify(orderRepository).findById(id);
        assertEquals(duplicate,duplicateOrderById);
    }

    @Test
    void findOrderById() {
        //given
        List<Activities> activities  = Collections.emptyList();
        List<Part> parts = Collections.emptyList();
        List<Employee> employeeList = Collections.emptyList();
        Client promont = new Client();
        Order order = new Order(2l, promont, employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(10), 3f, 12f, "brak", "test", "test", "");
        Long id = 2L;
        given(orderRepository.findById(id)).willReturn(Optional.of(order));

        //when
        Order result = orderService.findOrderById(id);

        //then
        verify(orderRepository).findById(id);
        assertEquals(order,result);
    }

    @Test
    void updateOrder() {
        //given
        List<Activities> activities  = Collections.emptyList();
        List<Part> parts = Collections.emptyList();
        List<Employee> employeeList = Collections.emptyList();
        Client promont = new Client();
        Long id = 2L;
        Order order = new Order(2l, promont, employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(10), 3f, 12f, "brak", "test", "test", "");
        Order update = new Order(2l, promont, employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(13), 0, 0, "brak", "test", "test", "");
        given(orderRepository.findById(id)).willReturn(Optional.of(order));
        given(orderRepository.save(any(Order.class))).willReturn(update);

        //when
        Order result = orderService.updateOrder(order);

        //then
        verify(orderRepository).findById(id);
        assertEquals(update,result);
    }

    @Test
    void throw_Resource_Not_Found_Exception_If_Employee_Not_Found_Update_Employee(){
        //given
        List<Activities> activities  = Collections.emptyList();
        List<Part> parts = Collections.emptyList();
        List<Employee> employeeList = Collections.emptyList();
        Client promont = new Client();
        Long id = 2L;
        Order order = new Order(2l, promont, employeeList, activities, parts, LocalDate.now(), LocalDate.now().plusDays(10), 3f, 12f, "brak", "test", "test", "");
        given(orderRepository.findById(id)).willReturn(Optional.empty());

        //when, then
        assertThrows(ResourceNotFoundException.class, () -> orderService.updateOrder(order));
        verify(orderRepository).findById(id);
        verify(orderRepository, never()).save(any(Order.class));
    }
}
