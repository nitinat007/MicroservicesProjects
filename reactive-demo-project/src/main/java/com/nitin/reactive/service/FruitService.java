package com.nitin.reactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/**
 * Author: kunitin
 * Created: 30/07/22
 * Info: service class for fruit controller class
 **/

@Service
public class FruitService {

    public Flux<String> getFruits(){
        return Flux.fromIterable(List.of("Mango", "Banana", "Guava"))
                .delayElements(Duration.ofSeconds(2))
                .doOnNext(s -> {
                    System.out.println("next item ..");
                }).log();
    }

    public Mono<String> getFruit(){
        return Mono.just("Mango");
    }

    private void end(){
        System.out.println("=======");
    }
}
