package com.example.springsecuritydemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    public String getGreeting(){
        return "Hello there. ";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public String user(){
        return "Hello user. ";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public String admin(){
        return "Hello admin. ";
    }
}
