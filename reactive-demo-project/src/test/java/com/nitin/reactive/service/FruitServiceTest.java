package com.nitin.reactive.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FruitServiceTest {

    FruitService service = new FruitService();

    @Test
    void getFruits() {
       var fruitsFlux   = service.getFruits();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango","Guava","Banana")
                .expectComplete();
    }

    @Test
    void getFruit() {
        var fruitsFlux   = service.getFruit();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango")
                .expectComplete();
    }
}