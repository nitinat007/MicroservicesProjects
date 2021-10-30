package com.example.KafkaStreamProcessingDemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "users-topic";

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(String message){
        logger.info("consumed: {}",message);
    }

}
