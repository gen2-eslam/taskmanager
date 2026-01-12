package com.example.taskmanager.services;

import java.util.List;
import java.util.UUID;

import com.example.taskmanager.domain.dto.TaskRequestDto;
import com.example.taskmanager.domain.entities.Task;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(TaskRequestDto taskRequestDto);
    Task updateTask(UUID taskId, TaskRequestDto taskRequestDto);
    void deleteTask(UUID taskId);

}
