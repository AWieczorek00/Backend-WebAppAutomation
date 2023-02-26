package pl.backendbscthesis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.backendbscthesis.Entity.Employee;
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
        List<Employee> employeeList = employeeService.findAllEmployees();
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }


    @GetMapping("/{individualId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long individualId){
        Employee employee = employeeService.findEmployeeByIndividualId(individualId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee employeeBody){
        Employee employee = employeeService.createNewEmployee(employeeBody);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> putEmployee(@RequestBody Employee employeeBody){
        Employee employee = employeeService.updateEmployee(employeeBody);
        return  new ResponseEntity<>(employee,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{individualId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long individualId){
        employeeService.deleteEmployeeById(individualId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
