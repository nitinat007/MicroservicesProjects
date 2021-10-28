package com.nitin.SampleSBApplication.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/*
Filters are part of the webserver and not the Spring framework.
 */
@Component
public class SampleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter | Remote Address:" + servletRequest.getRemoteAddr());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
