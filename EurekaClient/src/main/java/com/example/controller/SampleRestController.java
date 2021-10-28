package com.example.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {

    @RequestMapping("/")
    public String getGreeting(){
        return "Hello!  - from Eureka client";
    }
}
