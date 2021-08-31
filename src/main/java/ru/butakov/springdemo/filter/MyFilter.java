package ru.butakov.springdemo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/*")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter: i filtering...");
        ((HttpServletResponse) response).setHeader("MyFilter", "ok");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String pathInfo = httpServletRequest.getRequestURI();
        if (pathInfo != null && pathInfo.endsWith("/2")) {
            throw new MyException("MyFilter: i'm bad");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
