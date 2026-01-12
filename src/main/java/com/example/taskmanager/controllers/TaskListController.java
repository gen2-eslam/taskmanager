package com.example.taskmanager.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.domain.entities.TaskList;
import com.example.taskmanager.domain.dto.ResponseDto;
import com.example.taskmanager.domain.dto.TaskListDto;
import com.example.taskmanager.domain.dto.TaskListRequestDto;
import com.example.taskmanager.mappers.TaskListMapper;
import com.example.taskmanager.services.TaskListService;

@RestController
@RequestMapping("/task-lists")
public class TaskListController {
    
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public ResponseDto<List<TaskListDto>> listTaskLists() {
        return new ResponseDto<List<TaskListDto>>(
                "Task lists retrieved successfully",
                200,
                taskListService.listTaskList()
                        .stream()
                        .map(taskListMapper::toDto)
                        .toList());
    }

    @PostMapping("/create-task-list")
    public ResponseDto<TaskListDto> createTaskList(@RequestBody TaskListRequestDto taskRequest) {
        TaskList createdTaskList = taskListService.createTaskList(taskRequest.title(), taskRequest.description());
        return new ResponseDto<TaskListDto>(
                "Task list created successfully",
                201,
                taskListMapper.toDto(createdTaskList));
    }

    @GetMapping("/get-one-task-list")
    public ResponseDto<TaskListDto> getTaskListById(@RequestParam("task_list_id") UUID taskListId) {
        Optional<TaskList> taskList = taskListService.getTaskListById(taskListId);
        return new ResponseDto<TaskListDto>(
                "Task list retrieved successfully",
                200,
                taskListMapper.toDto(taskList.get()));
    }

    @PutMapping("/update-task-list")
    public ResponseDto<TaskListDto> updateTaskList(@RequestParam("task_list_id") UUID taskListId,
            @RequestBody TaskListRequestDto taskRequest) {
        TaskList updatedTaskList = taskListService.updateTaskList(taskListId, taskRequest.title(),
                taskRequest.description());
        return new ResponseDto<TaskListDto>(
                "Task list updated successfully",
                200,
                taskListMapper.toDto(updatedTaskList));
    }

    @DeleteMapping("/delete-task-list")
    public ResponseDto<LocalDateTime> deleteTaskList(@RequestParam("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
        return new ResponseDto<LocalDateTime>(
                "Task list deleted successfully",
                200,
                LocalDateTime.now());
    }
}
