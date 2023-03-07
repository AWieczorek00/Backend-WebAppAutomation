package pl.backendbscthesis.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import pl.backendbscthesis.Entity.*;
import pl.backendbscthesis.Repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;


    @Test
    void findAllTasks() {
        //given
        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L,"Test task",LocalDate.now().plusDays(1),false,employee));
        taskList.add(new Task(2L,"Test task",LocalDate.now().plusDays(10),true,employee));
        given(taskRepository.findAll()).willReturn(taskList);

        //when
        List<Task> result = taskService.findAllTasks();

        //then
        assertThat(result).isEqualTo(taskList);
    }

    @Test
    void findAllTaskForEmployeeByIndividualId() {
        //given
        Long id = 1234L;
        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L,"Test task",LocalDate.now().plusDays(1),false,employee));
        taskList.add(new Task(2L,"Test task",LocalDate.now().plusDays(10),true,employee));
        given(taskRepository.findAllByEmployeeIndividualId(id)).willReturn(Optional.of(taskList));

        //when
        List<Task> result = taskService.findAllTaskForEmployeeByIndividualId(id);

        //then
        assertThat(result).isEqualTo(taskList);
    }

    @Test
    void createTask() {
        //given
        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        Task task = new Task(1L, "Test task", LocalDate.now().plusDays(1), false, employee);
        given(taskRepository.save(task)).willReturn(task);

        //when
        Task result = taskService.createTask(task);

        //then
        verify(taskRepository).save(any(Task.class));
        assertEquals(task,result);
    }

    @Test
    void deleteTaskById() {
        //given
        Long id = 1L;

        //when
        taskService.deleteTaskById(id);

        //then
        verify(taskRepository).deleteById(id);
    }

    @Test
    void updateTask() {
        //given
        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        Long id = 1L;
        Task task = new Task(1L, "Test task", LocalDate.now().plusDays(1), false, employee);
        Task updateTask = new Task(1L, "Test", LocalDate.now().plusDays(3), false, employee);
        given(taskRepository.findById(id)).willReturn(Optional.of(task));
        given(taskRepository.save(any(Task.class))).willReturn(updateTask);

        //when
        Task result = taskService.updateTask(updateTask);

        //then
        verify(taskRepository).findById(id);
        verify(taskRepository).save(updateTask);
        assertEquals(updateTask,result);
    }

    @Test
    void taskCompletion() {
        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        Long id = 1L;
        Task task = new Task(1L, "Test task", LocalDate.now().plusDays(1), false, employee);
        Task doneTask = new Task(1L, "Test task", LocalDate.now().plusDays(1), true, employee);
        given(taskRepository.findById(id)).willReturn(Optional.of(task));
        given(taskRepository.save(any(Task.class))).willReturn(doneTask);

        //when
        Task result = taskService.taskCompletion(doneTask);

        //then
        verify(taskRepository).findById(id);
        verify(taskRepository).save(doneTask);
        assertEquals(doneTask,result);
    }

    @Test
    void throw_Resource_Not_Found_Exception_If_Task_Not_Found_Update_Task(){
        //given
        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        Long id = 1L;
        Task task = new Task(1L, "Test task", LocalDate.now().plusDays(1), false, employee);
        given(taskRepository.findById(id)).willReturn(Optional.empty());

        //when, then
        assertThrows(ResourceNotFoundException.class, () -> taskService.updateTask(task));
        verify(taskRepository).findById(id);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void throw_Resource_Not_Found_Exception_If_Task_Not_Found_Completion_Task(){
        //given
        Employee employee = new Employee(1234L, "Adam", "Andrzej", "Wieczorek", "adam98523@gmail.com", 123456789L, LocalDate.now());
        Long id = 1L;
        Task task = new Task(1L, "Test task", LocalDate.now().plusDays(1), false, employee);
        given(taskRepository.findById(id)).willReturn(Optional.empty());

        //when, then
        assertThrows(ResourceNotFoundException.class, () -> taskService.taskCompletion(task));
        verify(taskRepository).findById(id);
        verify(taskRepository, never()).save(any(Task.class));
    }
}
