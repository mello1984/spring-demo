package ru.butakov.springdemo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/api/path1/*")
public class Filter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterUtils.doFilter(request, response, chain, this.getClass().getSimpleName());
    }
}
