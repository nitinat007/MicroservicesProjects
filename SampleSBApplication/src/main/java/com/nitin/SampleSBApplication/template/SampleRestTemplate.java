package com.nitin.SampleSBApplication.template;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/*
RestTemplate class is a class to create REST client to consume REST APIs within Spring Application
 */
@Component
public class SampleRestTemplate {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
