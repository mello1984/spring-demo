package ru.butakov.springdemo.reeper1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = SpringDemoApplication.class)
class JohnSnowImplTest {
    @Autowired
    Heir heir;

    @Configuration
    @Import({JohnSnowImpl.class, InjectNedStarkDecisionBeanPostProcessor.class})
    static class TestConfiguration {
    }

    @Test
    void sayMessage() {
        var actual = heir.sayMessage();
        assertThat(actual).isEqualTo("John, you are bastard, you are John Snow");
    }
}