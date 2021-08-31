package ru.butakov.springdemo.filter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.WebFilterChain;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ServletComponentScan(basePackages = "ru.butakov.springdemo.filter")
@AutoConfigureMockMvc
class CustomControllerTest {
    @Autowired
    MockMvc mockMvc;

//    @Configuration
//    @Import({MyController.class, MyAdvice.class,})
//    @ServletComponentScan(basePackages = "ru.butakov.springdemo.filter")
//    static class NestedConfig {
//    }

    @Test
    void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.header().string("MyFilter", (String) null))
        ;
    }

    @Test
    void api1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/custom/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("MyFilter", "enabled"))
        ;
    }

    @Test
    void api2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/custom/2"))
                .andExpect(status().isBadRequest());
//                .andExpect(result -> assertThat(result.getResolvedException() instanceof MyException).isTrue());
//                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Прямой выброс exception из контроллера обрабатывается Advice")
    void noFilter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/no_filter/"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("MyController: i'm evil md"));
    }
}