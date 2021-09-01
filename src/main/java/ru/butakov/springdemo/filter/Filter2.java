package ru.butakov.springdemo.filter;

import javax.servlet.*;
import java.io.IOException;

public class Filter2 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterUtils.doFilter(request, response, chain, this.getClass().getSimpleName());
    }
}
