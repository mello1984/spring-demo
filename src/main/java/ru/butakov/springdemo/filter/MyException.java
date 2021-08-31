package ru.butakov.springdemo.filter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class MyException extends RuntimeException{
    public MyException(String message) {
        super(message);
    }
}