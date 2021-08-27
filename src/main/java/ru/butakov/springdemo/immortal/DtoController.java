package ru.butakov.springdemo.immortal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DtoController {

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/dto")
    public ResponseEntity<Dto> dto(@RequestBody Dto dto) {
        System.out.println("dto = " + dto);
        return ResponseEntity.ok(dto);
    }
}
