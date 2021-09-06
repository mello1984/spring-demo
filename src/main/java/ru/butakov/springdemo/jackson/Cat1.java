package ru.butakov.springdemo.jackson;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Cat1 {
    String name;
    int age;
    LocalDate birthday;
}
