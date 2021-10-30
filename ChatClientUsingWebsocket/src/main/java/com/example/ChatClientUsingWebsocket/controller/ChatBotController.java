package com.example.ChatClientUsingWebsocket.controller;

import com.example.ChatClientUsingWebsocket.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatBotController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message processRequest(Message receivedMessage) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(receivedMessage);
        try {
            return new Message("We are processing: " + HtmlUtils.htmlEscape(receivedMessage.getContent()));
        }catch (Exception e){
            System.out.println(e);
            return new Message("Default message ");
        }
    }

}
