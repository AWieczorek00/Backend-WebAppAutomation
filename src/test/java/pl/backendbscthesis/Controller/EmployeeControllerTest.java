package pl.backendbscthesis.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.backendbscthesis.Entity.Employee;
import pl.backendbscthesis.Service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void getAllEmployee() {
        //given
        List<Employee> employeeList = new ArrayList<>();
        Employee Adam = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        Employee Paulina = new Employee(5678L, "Paulina", "", "Żelek", "awieczorek0000@gmail.com", 123456789L, LocalDate.now());
        employeeList.add(Adam);
        employeeList.add(Paulina);
        given(employeeService.findAllEmployees()).willReturn(employeeList);

        //when
        ResponseEntity<List<Employee>> allEmployee = employeeController.getAllEmployee();
        List<Employee> result = allEmployee.getBody();

        //then
        assertEquals(HttpStatus.OK,allEmployee.getStatusCode());
        assertEquals(employeeList,result);
    }

    @Test
    void getEmployee() {
        //given
        Long id = 1234L;
        Employee Adam = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        given(employeeService.findEmployeeByIndividualId(id)).willReturn(Adam);

        //when
        ResponseEntity<Employee> responseEntityEmployee = employeeController.getEmployee(id);
        Employee result = responseEntityEmployee.getBody();

        //then
        assertEquals(HttpStatus.OK,responseEntityEmployee.getStatusCode());
        assertEquals(Adam,result);
    }

    @Test
    void createEmployee() {
        //given
        Employee Adam = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        given(employeeService.createNewEmployee(Adam)).willReturn(Adam);

        //when
        ResponseEntity<Employee> employeeRE = employeeController.createEmployee(Adam);
        Employee result = employeeRE.getBody();

        //then
        assertEquals(HttpStatus.CREATED,employeeRE.getStatusCode());
        assertEquals(Adam,result);
    }

    @Test
    void updateEmployee() {
        //given
        Employee update = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        given(employeeService.updateEmployee(update)).willReturn(update);

        //when
        ResponseEntity<Employee> employeeResponseEntity = employeeController.updateEmployee(update);
        Employee result = employeeResponseEntity.getBody();

        //then
        assertEquals(HttpStatus.OK, employeeResponseEntity.getStatusCode());
        assertEquals(update,result);
    }

    @Test
    void deleteEmployee() {
        //given
        Long id=1234L;

        //when
        ResponseEntity<?> responseEntity = employeeController.deleteEmployee(id);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(employeeService, times(1)).deleteEmployeeById(id);
    }
}
