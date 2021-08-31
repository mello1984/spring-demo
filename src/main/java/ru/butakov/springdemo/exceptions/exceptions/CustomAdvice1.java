package ru.butakov.springdemo.exceptions.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomAdvice1 {

    @ExceptionHandler(CustomException1.class)
    public ResponseEntity<Object> handle(CustomException1 e) {
        System.out.println("CustomAdvice1: i handle");
        return ResponseEntity.ok().build();
    }
}
