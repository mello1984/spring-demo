package ru.butakov.springdemo.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.butakov.springdemo.exceptions.exceptions.CustomAdvice1;
import ru.butakov.springdemo.exceptions.exceptions.CustomAdvice2;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class CustomControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Configuration
    @Import({CustomController.class, CustomAdvice1.class, CustomAdvice2.class})
    static class Config {
    }

    @Test
    void handle1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/path1"))
                .andExpect(status().isOk());
    }

    @Test
    void handle2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/path2"))
                .andExpect(status().isForbidden())
                .andExpect(MockMvcResultMatchers.content().string("CustomException2 handle"))
        ;
    }
}