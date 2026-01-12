package com.example.taskmanager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.taskmanager.domain.entities.TaskList;
import com.example.taskmanager.repositories.TaskListRepository;
import com.example.taskmanager.services.TaskListService;

import org.springframework.stereotype.Service;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskList() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(String title, String description) {
        if (null == title || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task List title must be present!");
        }

        LocalDateTime now = LocalDateTime.now();

        return taskListRepository.save(new TaskList(
                null,
                title,
                description,
                null,
                now,
                now));
    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        if (taskList.isEmpty()) {
            throw new IllegalArgumentException("Task List not found!");
        }
        return taskList;
    }

    @Override
    public TaskList updateTaskList(UUID id, String title, String description) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        if (taskList.isEmpty()) {
            throw new IllegalArgumentException("Task List not found!");
        }

        taskList.get().setTitle(title);
        taskList.get().setDescription(description);
        taskList.get().setUpdatedAt(LocalDateTime.now());
        return taskListRepository.save(taskList.get());
    }

    @Override
    public void deleteTaskList(UUID id) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        if (taskList.isEmpty()) {
            throw new IllegalArgumentException("Task List not found!");
        }
        taskListRepository.deleteById(id);
    }
}
