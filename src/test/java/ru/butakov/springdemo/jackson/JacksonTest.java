package ru.butakov.springdemo.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JacksonTest {

    @Test
    @DisplayName("LocalDate deserialize correctly")
    public void test1() throws JsonProcessingException {
        var cat = new Cat1("Tom", 1, LocalDate.of(2021, 1, 1));
        var mapper = JsonMapper.builder().findAndAddModules().build();
        var actual = mapper.writeValueAsString(cat);
        assertThat(actual).isEqualTo("{\"name\":\"Tom\",\"age\":1,\"birthday\":[2021,1,1]}");
    }

    @Test
    @DisplayName("Duration serialize to seconds correctly")
    public void test2() throws JsonProcessingException {
        var cat = new Cat2("Tom", 1, LocalDate.of(2021, 1, 1), Duration.of(3, ChronoUnit.SECONDS));
        var mapper = JsonMapper.builder().findAndAddModules().build();
        var actual = mapper.writeValueAsString(cat);
        assertThat(actual).isEqualTo("{\"name\":\"Tom\",\"age\":1,\"birthday\":[2021,1,1],\"sleep\":3.000000000}");
    }

    @Test
    @DisplayName("Duration deserialize to seconds correctly")
    public void test3() throws JsonProcessingException {
        var json = "{\"name\":\"Tom\",\"age\":1,\"birthday\":[2021,1,1],\"sleep\":\"PT2M5.821S\"}";
        var mapper = JsonMapper.builder().findAndAddModules().build();
        var cat = mapper.readValue(json, Cat2.class);
        System.out.println("cat = " + cat);
        assertThat(cat.getSleep().toSeconds()).isEqualTo(125);
    }

    @Test
    @DisplayName("Duration deserialize to long correctly")
    public void test4() throws JsonProcessingException {
        var json = "{\"name\":\"Tom\",\"age\":1,\"birthday\":[2021,1,1],\"sleep\":\"PT2M5.821S\"}";
        var mapper = JsonMapper.builder().findAndAddModules().build();
        var cat = mapper.readValue(json, Cat3.class);
        System.out.println("cat = " + cat);
        assertThat(cat.getSleep()).isEqualTo(125);
    }

    @Test
    @DisplayName("Duration serialize to yyyy-MM-dd correctly, but deserialization throws exception")
    public void test5() throws JsonProcessingException {
        var json = "{\"name\":\"Tom\",\"age\":1,\"birthday\":\"2021-01-01\"}";
        var mapper = JsonMapper.builder().findAndAddModules().build();
        var dateTime = LocalDateTime.of(2021, 1, 1, 8, 30);
        var cat = new Cat4("Tom", 1, dateTime);
        var jsonCat = mapper.writeValueAsString(cat);
        System.out.println("jsonCat = " + jsonCat);
        assertThat(jsonCat).isEqualTo(json);

        assertThatThrownBy(() -> mapper.readValue(json, Cat4.class))
                .isInstanceOf(InvalidFormatException.class);
    }

    @Test
    @DisplayName("JsonView serialize correctly")
    public void test6() throws JsonProcessingException {
        var cat = new Cat5("Tom", 1, LocalDate.of(2021, 1, 1));
        var mapper = JsonMapper.builder()
                .findAndAddModules()
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .build();

        var actual = mapper.writerWithView(Cat5.Id.class).writeValueAsString(cat);
        assertThat(actual).isEqualTo("{\"name\":\"Tom\"}");
    }

    @Test
    @DisplayName("JsonProperty serialize and deserialize correctly")
    public void test7() throws JsonProcessingException {
        var cat = new Cat6("Tom", 1, LocalDate.of(2021, 1, 1));
        var mapper = JsonMapper.builder().findAndAddModules().build();
        var actual = mapper.writeValueAsString(cat);
        String json = "{\"name\":\"Tom\",\"age\":1,\"day\":[2021,1,1]}";
        assertThat(actual).isEqualTo(json);

        var jsonCat = mapper.readValue(json, Cat6.class);
        System.out.println("jsonCat = " + jsonCat);
        assertThat(jsonCat.getBirthday()).isEqualTo(LocalDate.of(2021, 1, 1));
    }
}