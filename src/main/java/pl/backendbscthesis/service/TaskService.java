package pl.backendbscthesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.backendbscthesis.Entity.Employee;
import pl.backendbscthesis.Entity.Task;
import pl.backendbscthesis.Repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    public List<Task> findAllTaskByEmployee(Long individualId) {
        return taskRepository.findAllByEmployeeIndividualId(individualId).orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono żadnego zadania dla takie pracownika"));
    }

    public Task addTask(Task task) {
        task.setExecutionTime(task.getExecutionTime().plusDays(1));
        return taskRepository.save(task);
    }

    public void delete(Long id){
        taskRepository.deleteById(id);
    }

    public Task update(Task task){
        return taskRepository.findById(task.getId()).map(taskUpdate -> {
            taskUpdate.setName(task.getName());
            taskUpdate.setExecutionTime(task.getExecutionTime());
            taskUpdate.setEmployee(task.getEmployee());
            return taskRepository.save(taskUpdate);
        }).orElseThrow();
    }


    public Task doneTaskUpdate(Task taskBody) {
        return taskRepository.findById(taskBody.getId()).map(taskUpdate -> {
            taskUpdate.setDone(taskBody.getDone());
            return taskRepository.save(taskUpdate);
        }).orElseThrow();
    }
}
