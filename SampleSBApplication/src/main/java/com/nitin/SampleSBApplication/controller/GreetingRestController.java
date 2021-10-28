package com.nitin.SampleSBApplication.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    @Value("${springg.application.name:DefaultNameOfApplication}")
    private String appName;

    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    public String hello() {
        return "Hello World from Application: " + appName;
    }
}
