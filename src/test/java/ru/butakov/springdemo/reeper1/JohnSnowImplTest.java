package ru.butakov.springdemo.reeper1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.butakov.springdemo.reeper1.context.InjectNedStarkDecisionBeanPostProcessor;
import ru.butakov.springdemo.reeper1.context.PostProxyContextListener;
import ru.butakov.springdemo.reeper1.context.SendToTheWallBeanPostProcessor;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class JohnSnowImplTest {
    @Autowired
    Heir heir;

    @Configuration
    @Import({JohnSnowImpl.class,
            InjectNedStarkDecisionBeanPostProcessor.class,
            SendToTheWallBeanPostProcessor.class,
            PostProxyContextListener.class})
    static class TestConfiguration {
    }

    @Test
    void sayMessage() {
        heir.sayMessage();
    }
}