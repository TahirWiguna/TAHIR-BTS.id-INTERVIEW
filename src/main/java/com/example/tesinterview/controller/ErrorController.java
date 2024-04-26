package com.example.tesinterview.controller;

import com.example.tesinterview.Response;
import com.example.tesinterview.entity.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, List<String>>> constraintViolationException(ConstraintViolationException exception) {

        // Collect all validation error messages
        Map<String, List<String>> errors = exception.getConstraintViolations()
                .stream()
                .collect(Collectors.groupingBy(
                        violation -> violation.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())
                ));

        System.out.println(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Response<String>> responseStatusException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(Response.<String>builder().message(exception.getReason()).build());
    }
}
