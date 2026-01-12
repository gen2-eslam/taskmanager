package com.example.taskmanager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.taskmanager.domain.dto.TaskRequestDto;
import com.example.taskmanager.domain.entities.Task;
import com.example.taskmanager.domain.entities.TaskList;
import com.example.taskmanager.domain.entities.TaskPriority;
import com.example.taskmanager.domain.entities.TaskStatus;
import com.example.taskmanager.mappers.TaskMapper;
import com.example.taskmanager.repositories.TaskListRepository;
import com.example.taskmanager.repositories.TaskRepository;
import com.example.taskmanager.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        List<Task> tasks = taskRepository.findByTaskListId(taskListId);

        return tasks;
    }

    @Override
    public Task createTask(TaskRequestDto taskRequestDto) {

        TaskList taskList = getTaskListById(taskRequestDto.taskListId());
        Task task = new Task();
        task.setTitle(taskRequestDto.title());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setDueDate(taskRequestDto.dueDate());
        task.setDescription(taskRequestDto.description());
        task.setTaskList(taskList);
        task.setPriority(taskRequestDto.priority().orElse(TaskPriority.MEDIUM));
        task.setStatus(taskRequestDto.status().orElse(TaskStatus.OPEN));
        return taskRepository.save(task);
    }

    private TaskList getTaskListById(UUID taskListId) {

        Optional<TaskList> taskList = taskListRepository.findById(taskListId);
        if (taskList.isEmpty()) {
            throw new IllegalArgumentException("Task list not found");
        }
        return taskList.get();
    }

    @Override
    public Task updateTask(UUID taskId, TaskRequestDto taskRequestDto) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            throw new IllegalArgumentException("Task not found");
        }
        task.get().setTitle(taskRequestDto.title());
        task.get().setUpdatedAt(LocalDateTime.now());
        task.get().setDueDate(taskRequestDto.dueDate());
        task.get().setDescription(taskRequestDto.description());
        task.get().setPriority(taskRequestDto.priority().orElse(TaskPriority.MEDIUM));
        task.get().setStatus(taskRequestDto.status().orElse(TaskStatus.OPEN));
        return taskRepository.save(task.get());
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }

}
