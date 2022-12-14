package com.vmw.rmqclient.controller;

import com.vmw.rmqclient.model.RMQConnectionModel;
import com.vmw.rmqclient.utils.RMQOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void connectRmq(@RequestBody RMQConnectionModel rmqConnProp) throws Exception {
        log.info(" Initializing RMQ connection");
        rmqOperations.initializeRMQ(rmqConnProp);
    }

    @PostMapping(value = "/rmq-close-connection")
    public void disconnectRmq(@RequestBody RMQConnectionModel rmqConnProp) {
        rmqOperations.closeRMQConnection();

    }
}
