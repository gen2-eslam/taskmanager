package com.example.taskmanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.taskmanager.domain.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleException(RuntimeException runtimeException, WebRequest webRequest) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                runtimeException.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
