package com.nitin.SampleSBApplication.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
HandlerInterceptors are part of the Spring MVC framework and sit between the DispatcherServlet and our Controllers.
 */
@Component
public class SampleServiceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("** Pre Interceptor Handler called **");
        System.out.println("   " + request.getMethod() + " " + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("** Post Interceptor Handler called **");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("** After completion Interceptor Handler  called **");
        System.out.println("Status sent: " + response.getStatus() + " \n");
    }
}
