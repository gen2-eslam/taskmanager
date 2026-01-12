package com.example.taskmanager.domain.dto;

public record ResponseDto<T>(
        String message,
        int statusCode,
        T data) {

}
