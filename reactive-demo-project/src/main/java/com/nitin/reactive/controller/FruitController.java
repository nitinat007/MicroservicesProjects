package com.nitin.reactive.controller;

import com.nitin.reactive.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Author: kunitin
 * Created: 30/07/22
 * Info: Sample Controller class for fruit market APIs
 **/

@RestController
@RequestMapping("/fruit-market")
public class FruitController {

    @Autowired
    FruitService service;

    @GetMapping(value = "/fruits", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getFruits(){
        return service.getFruits();
    }

    @GetMapping("/fruit")
    public Mono<String> getFruit(){
        return service.getFruit();
    }
}
