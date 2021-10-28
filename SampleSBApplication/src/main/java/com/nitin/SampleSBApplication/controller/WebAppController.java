package com.nitin.SampleSBApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebAppController {

    @RequestMapping(value = "/greeting",method = RequestMethod.GET)
    public String getIndexPage() {
        return "greeting";
    }

    @RequestMapping("/index" )
    public String homeScreen() {
        return "index";
    }

    @RequestMapping("/viewproducts")
    public String viewProducts() {
        return "viewproducts";
    }
}
