package ru.butakov.springdemo.jackson;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Cat5 {
    @JsonView(Id.class)
    String name;
    int age;
    LocalDate birthday;

    public interface Id {
    }
}
