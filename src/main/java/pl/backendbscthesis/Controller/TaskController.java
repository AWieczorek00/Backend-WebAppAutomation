package pl.backendbscthesis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.backendbscthesis.Entity.Task;
import pl.backendbscthesis.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
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


    @PostMapping("/add")
    public ResponseEntity<Task> postTask(@RequestBody Task taskBody){
        Task task = taskService.addTask(taskBody);
        return new ResponseEntity<>(task,HttpStatus.CREATED);
    }


}
