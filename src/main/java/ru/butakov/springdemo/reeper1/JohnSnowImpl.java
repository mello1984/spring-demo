package ru.butakov.springdemo.reeper1;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.butakov.springdemo.reeper1.bpp.InjectNedStarkDecision;
import ru.butakov.springdemo.reeper1.bpp.SendToTheWall;

import javax.annotation.PostConstruct;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
@SendToTheWall
public class JohnSnowImpl implements Heir {
    @InjectNedStarkDecision(decision = "John, you are bastard, you are John Snow")
    String message;

    public JohnSnowImpl() {
        System.out.println("CONSTRUCTOR BEFORE: " + this.message);
        message = "I'm John Stark, i'm heir of Ned Stark";
        System.out.println("CONSTRUCTOR AFTER: " + this.message);
    }

    @PostConstruct
    public void init() {
        System.out.println("INIT BEFORE: " + this.message);
        message = "I'm John Stark, i'm yet heir of Ned Stark";
        System.out.println("INIT AFTER: " + this.message);
    }

    @Override
    public String sayMessage() {
        return message;
    }


}
