package ru.butakov.springdemo.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomAdvice2 {

    @ExceptionHandler(CustomException2.class)
    public ResponseEntity<String> handle(CustomException2 e) {
        System.out.println("CustomAdvice2: i handle");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("CustomException2 handle");
    }
}
