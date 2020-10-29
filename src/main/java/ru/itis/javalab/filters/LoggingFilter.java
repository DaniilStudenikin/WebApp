package ru.itis.javalab.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

        logger.info(request.getHeader("User-Agent"));
        logger.info(String.valueOf(request.getRequestURL()));

        filterChain.doFilter(servletRequest, servletResponse);

    }


    @Override
    public void destroy() {

    }
}
