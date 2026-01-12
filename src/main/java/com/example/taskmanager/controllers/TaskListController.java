package com.example.taskmanager.controllers;

import java.util.List;
import java.util.UUID;

import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.domain.entities.TaskList;
import com.example.taskmanager.domain.dto.ResponseDto;
import com.example.taskmanager.domain.dto.TaskListDto;
import com.example.taskmanager.domain.dto.TaskRequestDto;
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

    @PostMapping
    public ResponseDto<TaskListDto> createTaskList(@RequestBody TaskRequestDto taskRequest) {
        TaskList createdTaskList = taskListService.createTaskList(taskRequest.title(), taskRequest.description());
        return new ResponseDto<TaskListDto>(
                "Task list created successfully",
                201,
                taskListMapper.toDto(createdTaskList));
    }

    @GetMapping("/{task_list_id}")
    public ResponseDto<TaskListDto> getTaskListById(@PathVariable("task_list_id") UUID taskListId) {
        Optional<TaskList> taskList = taskListService.getTaskListById(taskListId);
        return new ResponseDto<TaskListDto>(
                "Task list retrieved successfully",
                200,
                taskListMapper.toDto(taskList.get()));
    }
}
