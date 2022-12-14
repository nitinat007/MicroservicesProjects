package com.vmw.rmqclient.model;

import lombok.Data;

/**
 * Author: kunitin
 * Created: 13/12/22
 * Info: DTO for configuring RMQ
 **/

@Data
public class RMQConfigureProperties {

    String queueName;

    String exchangeName;

    SupportedExchanges exchangeType;

    String routingKey;

}
