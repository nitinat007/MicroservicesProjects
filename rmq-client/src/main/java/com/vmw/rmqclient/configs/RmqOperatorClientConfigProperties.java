package com.vmw.rmqclient.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author: kunitin
 * Created: 12/12/22
 * Info: Auto Config properties for rmq operator client
 **/

@Configuration
@ConfigurationProperties("rmq-connection-config")
@Getter
@Setter
public class RmqOperatorClientConfigProperties {

    private String authenticationUrl;

    private String rmqHost;

    private int rmqPort;

    private String rmqQueue;

    private String clientId;

    private String clientSecret;

    private String grantType;

    private String orgId;

    private double refreshCredentialStrategy = 0.8;

}

