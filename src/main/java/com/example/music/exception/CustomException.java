package com.example.music.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@ControllerAdvice
public class CustomException {
    @ExceptionHandler(com.example.music.exception.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public com.example.music.exception.ErrorResponse handlerNotFoundException(com.example.music.exception.NotFoundException ex, WebRequest req){
        return new com.example.music.exception.ErrorResponse(HttpStatus.NOT_FOUND,ex.getMessage());
    }
}