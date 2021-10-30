package com.example.KafkaStreamProcessingDemo.service;

import com.example.KafkaStreamProcessingDemo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "users-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message){
        //this.kafkaTemplate.send(message,TOPIC);
        this.kafkaTemplate.send(TOPIC,message);
        logger.info("produced .. " + message);
    }

    public void addUser(User user){
        //this.kafkaTemplate.send(message,TOPIC);
        this.kafkaTemplate.send(TOPIC,user.toString());
        logger.info("produced .. " + user.toString());
    }
}
