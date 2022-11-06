package pl.backendbscthesis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.backendbscthesis.Entity.Employee;
import pl.backendbscthesis.Entity.Task;
import pl.backendbscthesis.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins="*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeList = employeeService.findAllEmployee();
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }


    @GetMapping("/{individualId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long individualId){
        Employee employee = employeeService.findEmployeeByIndividualId(individualId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee employeeBody){
        Employee employee = employeeService.addEmployee(employeeBody);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> putEmployee(@RequestBody Employee employeeBody){
        Employee employee = employeeService.updateEmployee(employeeBody, employeeBody.getIndividualId());
        return  new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PutMapping("/update/task/{individualId}")
    public ResponseEntity<Employee> putTaskToEmployee(@RequestBody List<Task> taskList, @PathVariable Long individualId){
        Employee employee = employeeService.putTaskToEmployee(individualId,taskList);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployee(@RequestBody Employee employeeBody){
        employeeService.deleteEmployee(employeeBody);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
