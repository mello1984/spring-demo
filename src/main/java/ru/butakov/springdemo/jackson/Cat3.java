package ru.butakov.springdemo.jackson;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cat3 {
    String name;
    int age;
    LocalDate birthday;
    long sleep;

    @JsonSetter
    public void setSleep(String sleep) {
        this.sleep = Duration.parse(sleep).toSeconds();
    }
}
