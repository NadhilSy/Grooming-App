package com.enigma.grooming.controller;

import com.enigma.grooming.exception.EntityExistException;
import com.enigma.grooming.exception.NotFoundException;
import com.enigma.grooming.exception.RestTemplateException;
import com.enigma.grooming.exception.UnauthorizedException;
import com.enigma.grooming.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EntityExistException.class)
    public ResponseEntity<ErrorResponse> handleEntityExistViolationException(EntityExistException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X03", exception.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", exception.getMessage()));
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("X99", exception.getMessage()));
    }
    @ExceptionHandler(RestTemplateException.class)
    ResponseEntity<ErrorResponse> restTemplateException(RestTemplateException exception){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorResponse("X07", exception.getMessage()));
    }
}
