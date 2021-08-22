package ru.butakov.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.butakov.springdemo.reeper1.Heir;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);
        var bean = context.getBean(Heir.class);
        System.out.println("Message = " + bean.sayMessage());
    }
}
