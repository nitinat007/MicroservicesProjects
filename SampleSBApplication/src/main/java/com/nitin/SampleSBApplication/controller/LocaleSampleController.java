package com.nitin.SampleSBApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
Internationalization example. Add language=fr query param with API to test french response
http://localhost:9090/locale?language=fr
http://localhost:9090/locale?language=hn
http://localhost:9090/locale
 */
@Controller
public class LocaleSampleController {
    @RequestMapping("/locale")
    public String locale() {
        return "locale";
    }
}
