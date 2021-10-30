package com.example.KafkaStreamProcessingDemo.controller;

import com.example.KafkaStreamProcessingDemo.model.User;
import com.example.KafkaStreamProcessingDemo.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class UsersController {
    @Autowired
    private Producer producer;

    @RequestMapping(method = RequestMethod.POST, value = "/publish")
    public void sendMessageToTopic(@RequestParam("message") String message ){
        producer.sendMessage(message);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public void addUser(@RequestBody User user ){
        producer.addUser(user);
    }
}
