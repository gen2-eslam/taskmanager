package com.example.taskmanager.domain.dto;

public record ErrorResponseDto(String message, int status, String details) {
    
}
