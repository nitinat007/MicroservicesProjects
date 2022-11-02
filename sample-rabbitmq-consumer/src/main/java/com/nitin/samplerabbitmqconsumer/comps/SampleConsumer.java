package com.nitin.samplerabbitmqconsumer.comps;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Author: kunitin
 * Created: 31/10/22
 * Info: Setup default consumer
 **/

@Component
public class SampleConsumer {

    @RabbitListener(queues = {"${queue.name}"})
    public void receiveQueueMessage(@Payload String messageBody){
        System.out.println("Message received: "+ messageBody.toString());
    }
}
