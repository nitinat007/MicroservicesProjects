package com.vmw.rmqclient.controller;

import com.vmw.rmqclient.model.RMQConnectionModel;
import com.vmw.rmqclient.utils.RMQOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: kunitin
 * Created: 14/12/22
 * Info: Controller to manage RMQ instance connection
 **/

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class RMQConnectionController {

    @Autowired
    RMQOperations rmqOperations;


    @PostMapping(value = "/rmq-initialize")
    public ResponseEntity<String> connectRmq(@RequestBody RMQConnectionModel rmqConnProp) throws Exception {
        log.info(" Initializing RMQ connection");
        return new ResponseEntity(rmqOperations.initializeRMQ(rmqConnProp), HttpStatus.OK);
    }

    @DeleteMapping(value = "/rmq-close-current-connection")
    public void disconnectRmq() {
        rmqOperations.closeRMQConnection();

    }
}
