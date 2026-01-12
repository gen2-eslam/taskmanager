package com.example.taskmanager.domain.dto;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.example.taskmanager.domain.entities.TaskPriority;
import com.example.taskmanager.domain.entities.TaskStatus;

import jakarta.annotation.Nullable;

public record TaskRequestDto(UUID taskListId, String title, String description,Optional<TaskPriority> priority,
        LocalDateTime dueDate, Optional<TaskStatus> status) {

}
