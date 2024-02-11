package com.swadesh.taskmanager.taskmanager.contollers;

import org.springframework.web.bind.annotation.RestController;

import com.swadesh.taskmanager.taskmanager.dtos.CreateTaskDTO;
import com.swadesh.taskmanager.taskmanager.dtos.ErrorResponseDTO;
import com.swadesh.taskmanager.taskmanager.dtos.UpdateTaskDTO;
import com.swadesh.taskmanager.taskmanager.entities.TaskEntity;
import com.swadesh.taskmanager.taskmanager.services.TaskDBService;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskDBService taskService;

    public TaskController(TaskDBService taskService){
        this.taskService =taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") int id) {
        var task = taskService.getTaskById(id);

        if(task==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @PostMapping("/create")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO taskDTO) throws ParseException {
        
        var task = taskService.addTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDeadline());

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Integer id, @RequestBody UpdateTaskDTO taskDTO) throws ParseException {
        
        var task = taskService.updateTask(id, taskDTO.getDescription(), taskDTO.getDeadline(), taskDTO.isCompleted());

        if(task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(ParseException e) {

        return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid format: " + e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e) {
        
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal server error"));
    }
}
