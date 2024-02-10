package com.swadesh.taskmanager.taskmanager.services;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.swadesh.taskmanager.taskmanager.entities.TaskEntity;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;

    public TaskEntity addTask(String title, String description, Date deadline) {
        TaskEntity  task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline); //Validate that date format is correct.
        task.setCompleted(false);
        tasks.add(task);
        taskId++;

        return task;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    public TaskEntity getTaskById(int id) {
        for (TaskEntity task : tasks){
            if(task.getId() ==id){
                return task;
            }
        }
        return null;
    }
}
