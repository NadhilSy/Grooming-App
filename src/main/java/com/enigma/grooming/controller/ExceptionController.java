package com.enigma.grooming.controller;

import com.enigma.grooming.exception.*;
import com.enigma.grooming.model.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EntityExistException.class)
    public ResponseEntity<ErrorResponse> handleEntityExistViolationException(EntityExistException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X03", exception.getMessage()));
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleJwtExpires(RuntimeException e){
        if (e.getMessage().equals("JWT token expired")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("401",e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("X01",e.getMessage()));
    }
    @ExceptionHandler(NeedApprovalException.class)
    public ResponseEntity<ErrorResponse> handleNeedApprovalException(NeedApprovalException e){
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("X80",e.getMessage()));
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgument(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();
        for (FieldError error: fieldErrors) {
            errors.add(error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("X02", errors.toString()));
    }
}
