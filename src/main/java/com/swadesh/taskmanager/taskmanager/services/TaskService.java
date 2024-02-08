package com.swadesh.taskmanager.taskmanager.services;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.swadesh.taskmanager.taskmanager.entities.TaskEntity;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;

    void addTask(String title, String description, String deadline) {
        TaskEntity  task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(new Date(deadline)); //Validate that date format is correct.

    }
}
