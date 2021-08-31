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

    @GetMapping("/api/custom/{id}")
    public ResponseEntity<String> api(@PathVariable int id) {
        return ResponseEntity.ok("api page, id=" + id);
    }

    @GetMapping("/no_filter/")
    public ResponseEntity<String> noFilter() {
        throw new MyException("MyController: i'm evil md");
    }
}
