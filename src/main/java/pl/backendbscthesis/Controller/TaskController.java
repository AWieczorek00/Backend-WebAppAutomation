package pl.backendbscthesis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.backendbscthesis.Entity.Employee;
import pl.backendbscthesis.Entity.Task;
import pl.backendbscthesis.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTask(){
        List<Task> taskList = taskService.findAllTask();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/employee/all")
    public ResponseEntity<List<Task>> getAllTaskByEmployee(@RequestBody Employee employeeBody){
        List<Task> taskList = taskService.findAllTaskByEmployee(employeeBody);
        return new ResponseEntity<>(taskList , HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Task> postTask(@RequestBody Task taskBody){
        Task task = taskService.addTask(taskBody);
        return new ResponseEntity<>(task,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    ResponseEntity<Task> putTask(@RequestBody Task taskBody){
        Task task = taskService.update(taskBody);
        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
