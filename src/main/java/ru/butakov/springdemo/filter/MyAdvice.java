package ru.butakov.springdemo.filter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyAdvice {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<Object> handle(MyException e) {
        System.out.println(this.getClass().getSimpleName()+" i handle!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
