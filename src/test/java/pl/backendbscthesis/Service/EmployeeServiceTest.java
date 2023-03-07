package pl.backendbscthesis.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import pl.backendbscthesis.Entity.Employee;
import pl.backendbscthesis.Repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void findEmployeeByIndividualId() {
        //given
        Long individualId = 1234L;
        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        given(employeeRepository.findByIndividualId(individualId)).willReturn(Optional.of(employee));

        //when
        Employee result = employeeService.findEmployeeByIndividualId(individualId);

        //then
        assertEquals(employee, result);
        verify(employeeRepository).findByIndividualId(individualId);
    }

    @Test
    void findEmployeeByIndividualIdNotFound(){
        //given
        Long individualId = 1234L;
        given(employeeRepository.findByIndividualId(individualId)).willReturn(Optional.empty());

        //when,then
        assertThrows(ResourceNotFoundException.class,()-> employeeService.findEmployeeByIndividualId(individualId));

    }

    @Test
    void findAllEmployees() {
        //given
        given(employeeRepository.findAll()).willReturn(prepareEmployeesData());
        //when
        List<Employee> result = employeeService.findAllEmployees();
        //then
        assertThat(result).isEqualTo(prepareEmployeesData());
        assertEquals(result.size(), prepareEmployeesData().size());
    }

    @Test
    void createNewEmployee() {
        //given
        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        given(employeeRepository.save(employee)).willReturn(employee);

        //when
        Employee result = employeeService.createNewEmployee(employee);

        //then
        assertEquals(employee,result);
        assertNotNull(result);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void updateEmployee() {
        //given
        Long individualId = 1234L;
        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        Employee employeeUpdate = new Employee(1234L, "Paweł", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        given(employeeRepository.findByIndividualId(individualId)).willReturn(Optional.of(employee));
        given(employeeRepository.save(any(Employee.class))).willReturn(employeeUpdate);

        //when
        Employee result = employeeService.updateEmployee(employeeUpdate);

        //then
        verify(employeeRepository).findByIndividualId(individualId);
        verify(employeeRepository).save(employeeUpdate);
        assertEquals("Paweł", result.getFirstName());
        assertEquals("Wieczorek", result.getLastName());
    }

    @Test
    void throwResourceNotFoundExceptionIfEmployeeNotFoundUpdateEmployee(){
        //given
        Long individualId = 1234L;
        Employee updatedEmployee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        given(employeeRepository.findByIndividualId(individualId)).willReturn(Optional.empty());

        //when, then
        assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployee(updatedEmployee));
        verify(employeeRepository).findByIndividualId(individualId);
        verify(employeeRepository, never()).save(any(Employee.class));
    }


    @Test
    void deleteEmployeeById() {
        // given
        Long individualId = 123L;

        // when
        employeeService.deleteEmployeeById(individualId);

        // then
        verify(employeeRepository).deleteById(individualId);
    }


    private List<Employee> prepareEmployeesData() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1234L, "Adam", "Andrzej",
                "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now()));
        employeeList.add(new Employee(5678L, "Paulina", "", "Żelek", "awieczorek0000@gmail.com", 123456789L, LocalDate.now()));
        return employeeList;
    }
}
