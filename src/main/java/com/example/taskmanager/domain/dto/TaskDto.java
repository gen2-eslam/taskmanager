package com.example.taskmanager.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.taskmanager.domain.entities.TaskPriority;
import com.example.taskmanager.domain.entities.TaskStatus;

public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDateTime dueDate,
    TaskPriority priority,
    TaskStatus status
){}