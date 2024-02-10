package com.swadesh.taskmanager.taskmanager.entities;

import java.sql.Date;

import lombok.Data;

@Data
public class TaskEntity {
    private int id;
    private String description;
    private String title;
    private Date deadline;
    private boolean completed;
}
