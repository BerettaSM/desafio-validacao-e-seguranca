package com.devsuperior.bds04.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.devsuperior.bds04.dto.ValidationError;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        Instant timestamp = Instant.now();
        String message = "Validation error(s)";
        String path = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
            .getRequest().getRequestURI();
        ValidationError err = new ValidationError(status.value(), timestamp, message, path);
        for (FieldError fieldError: e.getFieldErrors()) {
            err.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(err.getStatus()).body(err);
    }
    
}
