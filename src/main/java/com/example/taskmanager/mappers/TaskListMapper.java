package com.example.taskmanager.mappers;

import com.example.taskmanager.domain.entities.TaskList;
import com.example.taskmanager.domain.dto.TaskListDto;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
