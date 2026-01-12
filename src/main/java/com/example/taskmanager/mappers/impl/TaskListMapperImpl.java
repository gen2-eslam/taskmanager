package com.example.taskmanager.mappers.impl;

import com.example.taskmanager.domain.entities.Task;
import com.example.taskmanager.domain.entities.TaskList;
import com.example.taskmanager.domain.entities.TaskStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.taskmanager.domain.dto.TaskListDto;
import com.example.taskmanager.mappers.TaskListMapper;
import com.example.taskmanager.mappers.TaskMapper;

@Component
public class TaskListMapperImpl implements TaskListMapper {
        private final TaskMapper taskMapper;

        public TaskListMapperImpl(TaskMapper taskMapper) {
                this.taskMapper = taskMapper;
        }

        @Override
        public TaskList fromDto(TaskListDto taskListDto) {
                return new TaskList(
                                taskListDto.id(),
                                taskListDto.title(),
                                taskListDto.description(),
                                Optional.ofNullable(taskListDto.tasks())
                                                .map(tasks -> tasks.stream().map(taskMapper::fromDto).toList())
                                                .orElse(List.of()),
                                null,
                                null);
        }

        @Override
        public TaskListDto toDto(TaskList taskList) {
                return new TaskListDto(
                                taskList.getId(),
                                taskList.getTitle(),
                                taskList.getDescription(),
                                Optional.ofNullable(taskList.getTasks())
                                                .map(List::size)
                                                .orElse(0),
                                calculateTaskListProgress(taskList.getTasks()),
                                Optional.ofNullable(taskList.getTasks())
                                                .map(tasks -> tasks.stream()
                                                                .map(taskMapper::toDto).toList())
                                                .orElse(List.of()));
        }

        private double calculateTaskListProgress(List<Task> tasks) {
                if (tasks == null || tasks.isEmpty()) {
                        return 0;
                }

                long closedTaskCount = tasks.stream()
                                .filter(task -> TaskStatus.CLOSED == task.getStatus())
                                .count();
                return (double) closedTaskCount / tasks.size();
        }
}
