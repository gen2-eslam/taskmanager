package com.example.taskmanager.mappers;

import com.example.taskmanager.domain.entities.Task;
import com.example.taskmanager.domain.dto.TaskDto;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
