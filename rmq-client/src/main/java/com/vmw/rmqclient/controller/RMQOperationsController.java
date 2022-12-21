package com.vmw.rmqclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vmw.rmqclient.configs.OperationsSupported;
import com.vmw.rmqclient.model.QueueMessage;
import com.vmw.rmqclient.model.RMQClientProperties;
import com.vmw.rmqclient.model.RMQConfigureProperties;
import com.vmw.rmqclient.utils.RMQOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: kunitin
 * Created: 30/11/22
 * Info: Controller class for all RMQ client operations
 **/

@RestController
@RequestMapping("/api/v1/rmq-client")
public class RMQOperationsController {

    @Autowired
    RMQOperations rmqOperations;


    @PostMapping(value = "/write")
    public ResponseEntity publish(@RequestBody RMQClientProperties clientInfo) throws JsonProcessingException {

        if (clientInfo.getMsg() == null) {
            return new ResponseEntity("message missing in body", HttpStatus.BAD_REQUEST);
        }
        if (clientInfo.getQueueName() != null && clientInfo.getQueueName().length() > 0) {
            rmqOperations.sendToQueue(clientInfo.getQueueName(), clientInfo.getMsg());
            return new ResponseEntity("message sent to queue", HttpStatus.ACCEPTED);
        } else if (clientInfo.getExchange() != null && clientInfo.getExchange().length() > 0) {
            // send to direct & fanout exchange
            rmqOperations.sendToExchange(clientInfo.getExchange(), clientInfo.getRoutingKey(), clientInfo.getMsg());
            return new ResponseEntity("message sent to exchange", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Queue or Exchange not passed in body", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/read/{queueName}")
    public ResponseEntity<?> subscribe(@PathVariable String queueName) throws JsonProcessingException {
        return new ResponseEntity<QueueMessage>(rmqOperations.readFromQueue(queueName), HttpStatus.OK);
    }

    /**
     * @param performOperation
     * @param rmqConfigProps   Supported operations:
     *                         declare queue (delete and re create)
     *                         declare Exchange (direct + routing key)& ( fanout) type
     *                         delete queue
     *                         delete exchange
     */
    @PostMapping(value = "/configure")
    public ResponseEntity<?> configure(@RequestParam OperationsSupported performOperation, @RequestBody RMQConfigureProperties rmqConfigProps) {
        ResponseEntity responseEntity;
        if (performOperation == OperationsSupported.CREATE_QUEUE) {
            boolean result = rmqOperations.createQueue(rmqConfigProps.getQueueName());
            if (result == true) {
                return new ResponseEntity("Created Queue ", HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        if (performOperation == OperationsSupported.DELETE_QUEUE) {
            boolean result = rmqOperations.deleteQueue(rmqConfigProps.getQueueName());
            if (result == true)
                return new ResponseEntity("Deleted Queue ", HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (performOperation == OperationsSupported.CREATE_EXCHANGE) {
            rmqOperations.createExchange(rmqConfigProps.getExchangeType(), rmqConfigProps.getExchangeName(), rmqConfigProps.getQueueName(), rmqConfigProps.getRoutingKey());
            return new ResponseEntity("Request submitted to create exchange", HttpStatus.ACCEPTED);
        }
        if (performOperation == OperationsSupported.DELETE_EXCHANGE) {
            boolean result = rmqOperations.deleteExchange(rmqConfigProps.getExchangeName());
            if (result == true)
                return new ResponseEntity("Deleted Exchange ", HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity("Request not submitted ", HttpStatus.CONFLICT);
    }


}
