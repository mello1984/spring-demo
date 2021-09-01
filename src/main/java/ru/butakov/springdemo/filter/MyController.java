package ru.butakov.springdemo.filter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("index page");
    }

    @GetMapping("/throw")
    public ResponseEntity<String> noFilter() {
        throw new MyException("MyController: i'm evil md");
    }

    @GetMapping("/api/path1/{id}")
    public ResponseEntity<String> api1(@PathVariable int id) {
        return ResponseEntity.ok("/api/path1/ page, id=" + id);
    }

    @GetMapping("/api/path2/{id}")
    public ResponseEntity<String> api2(@PathVariable int id) {
        return ResponseEntity.ok("/api/path2/ page, id=" + id);
    }
}
