package com.example.taskmanager.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.taskmanager.domain.entities.TaskList;

public interface TaskListService {
    List<TaskList> listTaskList();
    TaskList createTaskList(String title, String description);
    Optional<TaskList> getTaskListById(UUID id);
    TaskList updateTaskList(UUID id, String title, String description);
    void deleteTaskList(UUID id);
}
