package com.vmw.rmqclient.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmw.rmqclient.configs.RmqOperatorClientConfigProperties;
import com.vmw.rmqclient.model.QueueMessage;
import com.vmw.rmqclient.model.RMQConnectionModel;
import com.vmw.rmqclient.model.SupportedExchanges;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Author: kunitin
 * Created: 12/12/22
 * Info: all operations performed by RMQ client
 **/

@Component
@Slf4j
public class RMQOperations {

    @Autowired
    //RabbitTemplate rabbitTemplate;
    ObjectProvider<RabbitTemplate> rabbitTemplateObjectProvider;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RmqOperatorClientConfigProperties rmqOperatorClientConfigProperties;

    //connect to RMQ
    public void initializeRMQ(RMQConnectionModel rmqConnProp) {
        rmqOperatorClientConfigProperties.setRmqHost(rmqConnProp.getRmqClusterUrl());
        if (rmqConnProp.getRmqClusterPort() != null)
            rmqOperatorClientConfigProperties.setRmqPort(rmqConnProp.getRmqClusterPort());
        rmqOperatorClientConfigProperties.setOrgId(rmqConnProp.getOrgId());
        log.info("rmqConnProp.getOAuth2AppProp() {}", rmqConnProp.getOAuth2AppProp());
        if (rmqConnProp.getOAuth2AppProp() != null) {
            rmqOperatorClientConfigProperties.setClientId(rmqConnProp.getOAuth2AppProp().getClientID());
            rmqOperatorClientConfigProperties.setClientSecret(rmqConnProp.getOAuth2AppProp().getClientSecret());
            rmqOperatorClientConfigProperties.setGrantType(rmqConnProp.getOAuth2AppProp().getGrantType());
        }
        rabbitTemplate = rabbitTemplateObjectProvider.getObject();
        log.info("Created RabbitTemplate UUID {}", rabbitTemplate.getUUID());
    }

    public void closeRMQConnection() {
        log.info("Destroying connection {} ..", rabbitTemplate.getUUID());
        rabbitTemplate.getConnectionFactory().resetConnection();
    }


    // queue operations
    public void sendToQueue(String queueName, QueueMessage message) throws JsonProcessingException {
        log.info("Sending message to queue {}", queueName);
//        log.info("queueExists ? {}", queueExists(queueName));
        rabbitTemplate.convertAndSend(queueName, new ObjectMapper().writeValueAsString(message));
    }

    public boolean queueExists(String queueName) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        return !Objects.isNull(rabbitAdmin.getQueueInfo(queueName));
    }

    public boolean deleteQueue(String queueName) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        return rabbitAdmin.deleteQueue(queueName);
    }

    public boolean createQueue(String queueName) {
        log.info("Creating a new queue {} with default exchange", queueName);
        Queue queue = new Queue(queueName, true, false, false);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        rabbitAdmin.declareQueue(queue);
        return !Objects.isNull(queueExists(queueName));
    }

    public QueueMessage readFromQueue(String queueName) throws JsonProcessingException {
        log.info("Reading message log from Queue {} ", queueName);
        Object readMessage = rabbitTemplate.receiveAndConvert(queueName);
        log.info("message read: {}", readMessage);
        if (readMessage == null) {
            return null;
        }
        return new ObjectMapper().readValue(readMessage.toString(), QueueMessage.class);
    }

    // Exchange Operations
    public void createExchange(SupportedExchanges exchangeType, String exchangeName, String onQueueName, String routingKey) {
        log.info("Creating a new exchange {} of type {}", exchangeName, exchangeType);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        Exchange exchange;
        if (exchangeType == SupportedExchanges.DIRECT)
            exchange = new DirectExchange(exchangeName);
        else
            exchange = new FanoutExchange(exchangeName);
        rabbitAdmin.declareExchange(exchange);
        Binding binding = new Binding(onQueueName, Binding.DestinationType.QUEUE, exchange.getName(), routingKey, null);
        rabbitAdmin.declareBinding(binding);
    }

    public void sendToExchange(String exchangeName, String routingKey, Object message) {
        log.info("Sending message to exchange {} , routingKey {} , message {}", exchangeName, routingKey, message);
        if (exchangeName != null)
            rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        else {
            log.info("sending msg wo exchange");
            rabbitTemplate.convertSendAndReceive(routingKey, message);
        }

    }

    public boolean deleteExchange(String exchangeName) {
        log.info("deleting Exchange {}", exchangeName);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        return rabbitAdmin.deleteExchange(exchangeName);
    }

    /*
    public boolean exchangeExists(String exchangeName) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        return !Objects.isNull(rabbitAdmin.getExchangeInfo(exchangeName));
    }

     */


}
