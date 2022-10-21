package pl.backendbscthesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backendbscthesis.Entity.Employee;
import pl.backendbscthesis.Entity.Task;
import pl.backendbscthesis.Repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final TaskService taskService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, TaskService taskService) {
        this.employeeRepository = employeeRepository;
        this.taskService = taskService;
    }

    public Employee findEmployeeByIndividualId(Long individualId) {
        return employeeRepository.findByIndividualId(individualId)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono pracownika o id: " + individualId));
    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employeeBody) {
        Random random = new Random();

        employeeBody.setIndividualId(Long.valueOf(String.valueOf(LocalDate.now()).replace("-","")+random.nextLong(10,100)));

        return employeeRepository.save(employeeBody);
    }

    @Transactional
    public Employee updateEmployee(Employee employeeBody, Long individualId) {
        return employeeRepository.findByIndividualId(individualId)
                .map(employeeUpdate -> {
                    employeeUpdate.setFirstName(employeeBody.getFirstName());
                    employeeUpdate.setSecondName(employeeBody.getSecondName());
                    employeeUpdate.setLastName(employeeBody.getLastName());
                    employeeUpdate.setPesel(employeeBody.getPesel());
                    employeeUpdate.setPhoneNumber(employeeBody.getPhoneNumber());
                    return employeeRepository.save(employeeUpdate);
                }).orElseThrow(() -> new ResourceNotFoundException("Nie zaleziono takiego pracownika"));
    }

    public Employee putTaskToEmployee(Long individualId, List<Task> taskList) {


//        List<Task> taskList1 = taskService.putEmployeeToTask(taskList,employeeRepository.findByIndividualId(individualId).orElseThrow(null));


        return employeeRepository.findByIndividualId(individualId).map(
                employee -> {
                    employee.setTask(taskList);
                    return employeeRepository.save(employee);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Nie zaleziono takiego pracownika"));
    }

    public void deleteEmployee(Employee employee){
        employeeRepository.delete(employee);
    }

}
