package ru.butakov.springdemo.reeper2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.butakov.springdemo.reeper2.context.ChangeToBeanFactoryPostProcessor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BeanFactoryPostProcessorTest {

    @Autowired
    List<Prank> boys;

    @Configuration
    @Import({TomSawyer.class, SlaveJim.class, ChangeToBeanFactoryPostProcessor.class})
    static class Config {
    }

    @Test
    void contextContainsJoeAndHuckleberryButNotTomAndJim() {
        boys.forEach(Prank::playPrank);
        assertThat(boys.size()).isEqualTo(2);
        assertThat(boys.stream().anyMatch(p -> p.getClass().getSimpleName().equals(JoeHarper.class.getSimpleName()))).isTrue();
        assertThat(boys.stream().anyMatch(p -> p.getClass().getSimpleName().equals(HuckleberryFinn.class.getSimpleName()))).isTrue();
    }
}