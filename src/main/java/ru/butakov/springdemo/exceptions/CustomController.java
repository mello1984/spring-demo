package ru.butakov.springdemo.exceptions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.butakov.springdemo.exceptions.exceptions.CustomException1;
import ru.butakov.springdemo.exceptions.exceptions.CustomException2;

@RestController
public class CustomController {

    @GetMapping("/path1")
    public String handle1() {
        throw new CustomException1("custom exception 1");
    }

    @GetMapping("/path2")
    public String handle2() {
        throw new CustomException2("custom exception 2");
    }
}
