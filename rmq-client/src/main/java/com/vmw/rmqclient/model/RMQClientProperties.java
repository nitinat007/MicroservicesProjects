package com.vmw.rmqclient.model;

import lombok.Data;

/**
 * Author: kunitin
 * Created: 30/11/22
 * Info: DTO for interaction with RMQ instance
 **/

@Data
public class RMQClientProperties {

    String queueName;

    String exchange;

    String routingKey;

    QueueMessage msg;

}
