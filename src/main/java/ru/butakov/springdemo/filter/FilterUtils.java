package ru.butakov.springdemo.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterUtils {
    static void doFilter(ServletRequest request, ServletResponse response, FilterChain chain, String className) throws IOException, ServletException {
        ((HttpServletResponse) response).setHeader(className, "checked");
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        System.out.println(className + ": i filtering: " + requestURI);
        if (requestURI != null && requestURI.endsWith("/2")) {
            throw new MyException(className + ": i'm bad");
        }
        chain.doFilter(request, response);
    }
}
