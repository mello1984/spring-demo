package ru.butakov.springdemo.filter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class CustomControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Configuration
    @Import({MyController.class, MyAdvice.class, ConfigF.class})
    @ServletComponentScan(basePackages = "ru.butakov.springdemo.filter")
    static class NestedConfig {
    }

    @Test
    @DisplayName("Фильтры не обрабатывают путь /")
    void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.header().string(Filter1.class.getSimpleName(), (String) null))
                .andExpect(MockMvcResultMatchers.header().string(Filter2.class.getSimpleName(), (String) null))
        ;
    }

    @Test
    @DisplayName("Фильтры не обрабатывают путь /throw, прямой выброс exception из контроллера обрабатывается Advice")
    void noFilterThrow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/throw"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.header().string(Filter1.class.getSimpleName(), (String) null))
                .andExpect(MockMvcResultMatchers.header().string(Filter2.class.getSimpleName(), (String) null))
                .andExpect(MockMvcResultMatchers.content().string("MyController: i'm evil md"));
    }

    @Test
    @DisplayName("Filter1 (из FilterRegistrationBean) работает внутри EmbeddedServer и фильтрует /api/path1, но в тесте мы этого не знаем")
        // Несмотря на то, что на самом деле фильтр работает (можно проверить постманом), mockMvc не вызывает embeddedServer
        // и мы не отлавливаем измененный хедер на уровне теста
    void apiFilter1_1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/path1/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.header().string(Filter1.class.getSimpleName(), (String) null))
                .andExpect(MockMvcResultMatchers.header().string(Filter2.class.getSimpleName(), (String) null))
        ;
    }

    @Test
    @DisplayName("Filter1 (из FilterRegistrationBean) работает внутри EmbeddedServer и фильтрует /api/path1, но в тесте мы этого не знаем и Advice не работает")
        // Несмотря на то, что на самом деле фильтр работает (можно проверить постманом), mockMvc не вызывает embeddedServer
        // и потому мы не отлавливаем измененный хедер
    void apiFilter1_2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/path1/2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.header().string(Filter1.class.getSimpleName(), (String) null))
                .andExpect(MockMvcResultMatchers.header().string(Filter2.class.getSimpleName(), (String) null))
        ;
    }

    @Test
    @DisplayName("Filter2 (из FilterRegistrationBean) работает внутри Spring и фильтрует /api/path2")
    void apiFilter2_1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/path2/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.header().string(Filter1.class.getSimpleName(), (String) null))
                .andExpect(MockMvcResultMatchers.header().string(Filter2.class.getSimpleName(), "checked"))
        ;
    }

    @Test
    @DisplayName("Filter2 (из FilterRegistrationBean) работает внутри Spring, фильтрует /api/path2, но Exception не обрабатывается Advice")
    void apiFilter2_2() throws Exception {
        assertThatThrownBy(() -> mockMvc.perform(MockMvcRequestBuilders.get("/api/path2/2")))
                .isInstanceOf(MyException.class)
                .hasMessage(Filter2.class.getSimpleName() + ": i'm bad");
    }
}