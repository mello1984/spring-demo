package ru.butakov.springdemo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan
public class ConfigF {

    @Bean
    public FilterRegistrationBean<Filter2> registrationFilter() {
        var registrationBean = new FilterRegistrationBean<Filter2>();
        registrationBean.setFilter(new Filter2());
        registrationBean.addUrlPatterns("/api/path2/*");
        return registrationBean;
    }
}
