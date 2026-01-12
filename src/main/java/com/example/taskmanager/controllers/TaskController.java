package com.example.taskmanager.controllers;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;
import com.example.taskmanager.domain.entities.Task;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.domain.dto.ResponseDto;
import com.example.taskmanager.domain.dto.TaskDto;
import com.example.taskmanager.domain.dto.TaskRequestDto;
import com.example.taskmanager.mappers.TaskMapper;
import com.example.taskmanager.services.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/list-tasks")
    public ResponseDto<List<TaskDto>> listTasks(@RequestParam("task_list_id") UUID taskListId) {
        List<Task> tasks = taskService.listTasks(taskListId);
        return new ResponseDto<List<TaskDto>>(
                "Retrieved tasks",
                200,
                tasks.stream().map(taskMapper::toDto).toList());
    }

    @PostMapping("/create-task")
    public ResponseDto<TaskDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {

        Task task = taskService.createTask(taskRequestDto);
        return new ResponseDto<TaskDto>("Task created successfully", 201, taskMapper.toDto(task));
    }

    @PutMapping("/update-task")
    public ResponseDto<TaskDto> updateTask(@RequestParam("task_id") UUID taskId,
            @RequestBody TaskRequestDto taskRequestDto) {

        Task task = taskService.updateTask(taskId, taskRequestDto);
        return new ResponseDto<TaskDto>("Task updated successfully", 200, taskMapper.toDto(task));
    }

    @DeleteMapping("/delete-task")
    public ResponseDto<LocalDateTime> deleteTask(@RequestParam("task_id") UUID taskId) {

        taskService.deleteTask(taskId);
        return new ResponseDto<LocalDateTime>("Task deleted successfully", 200, LocalDateTime.now());
    }

}
