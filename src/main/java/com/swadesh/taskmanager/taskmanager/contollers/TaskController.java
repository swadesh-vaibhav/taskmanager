package com.swadesh.taskmanager.taskmanager.contollers;

import org.springframework.web.bind.annotation.RestController;

import com.swadesh.taskmanager.taskmanager.dtos.CreateTaskDTO;
import com.swadesh.taskmanager.taskmanager.entities.TaskEntity;
import com.swadesh.taskmanager.taskmanager.services.TaskService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
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
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO taskDTO) {
        
        var task = taskService.addTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDeadline());

        return ResponseEntity.ok(task);
    } 
}
