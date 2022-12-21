package com.vmw.rmqclient.configs;

import com.rabbitmq.client.impl.CredentialsProvider;
import com.rabbitmq.client.impl.DefaultCredentialsRefreshService;
import com.rabbitmq.client.impl.OAuth2ClientCredentialsGrantCredentialsProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

/**
 * Author: kunitin
 * Created: 12/12/22
 * Info: All configurations here
 **/

@Configuration
@Slf4j
@Lazy
public class Configurations {

    private final static String ORG_ID = "orgId";
    private final RabbitProperties rabbitProperties = new RabbitProperties();
    @Autowired
    RmqOperatorClientConfigProperties rmqOperatorClientConfigProperties;
    @Value("${local.rabbitmq.username}")
    private String username;
    @Value("${local.rabbitmq.password}")
    private String password;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() throws Exception {
        log.info("initializing rabbitTemplate ...");
        RabbitConnectionFactoryBean rabbitConnectionFactoryBean = getConnectionFactoryBean();
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory(rabbitConnectionFactoryBean));
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.getConnectionFactory().clearConnectionListeners();
        log.info("UUID :{}", rabbitTemplate.getUUID());
        return rabbitTemplate;
    }


    @Bean
    Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        log.info("initializing Jackson2JsonMessageConverter ....");
        return new Jackson2JsonMessageConverter();
    }

    public CachingConnectionFactory cachingConnectionFactory(
            RabbitConnectionFactoryBean rabbitConnectionFactoryBean) throws Exception {
        log.info("initializing cachingConnectionFactory ....");
        return new CachingConnectionFactory(rabbitConnectionFactoryBean.getObject());
    }

    public RabbitConnectionFactoryBean getConnectionFactoryBean() throws NoSuchAlgorithmException, KeyManagementException {
        log.info("initializing getConnectionFactoryBean ....");
        RabbitConnectionFactoryBean rabbitConnectionFactoryBean = new RabbitConnectionFactoryBean();

        rabbitConnectionFactoryBean.setPort(rmqOperatorClientConfigProperties.getRmqPort());

        rabbitConnectionFactoryBean.setHost(rmqOperatorClientConfigProperties.getRmqHost());
        //Instead of above line, below can also be used
        //rabbitConnectionFactoryBean.setUri("amqp://"+rmqOperatorClientConfigProperties.getRmqHost());

        if (rmqOperatorClientConfigProperties.getClientId() != null && !rmqOperatorClientConfigProperties.getOrgId().equalsIgnoreCase("string")) {
            rabbitConnectionFactoryBean.setCredentialsProvider(credentialsProvider());
            rabbitConnectionFactoryBean.setCredentialsRefreshService(defaultCredentialsRefreshService());
            rabbitConnectionFactoryBean.setUseSSL(true);
            log.info("Skipping Certificate validation ..");
            rabbitConnectionFactoryBean
                    .setSkipServerCertificateValidation(true);
            rabbitConnectionFactoryBean.setEnableHostnameVerification(false);
        } else {
            //added for local testing
            rabbitConnectionFactoryBean.setUseSSL(false);
            rabbitConnectionFactoryBean.setUsername(username);
            rabbitConnectionFactoryBean.setPassword(password);
        }

        rabbitConnectionFactoryBean.afterPropertiesSet();
        return rabbitConnectionFactoryBean;
    }

    public CredentialsProvider credentialsProvider() throws NoSuchAlgorithmException, KeyManagementException {
        log.info("initializing credentialsProvider ....");

        return new OAuth2ClientCredentialsGrantCredentialsProvider.OAuth2ClientCredentialsGrantCredentialsProviderBuilder()
                .tokenEndpointUri(rmqOperatorClientConfigProperties.getAuthenticationUrl())
                .clientId(rmqOperatorClientConfigProperties.getClientId())
                .clientSecret(rmqOperatorClientConfigProperties.getClientSecret())
                .grantType(rmqOperatorClientConfigProperties.getGrantType())
                .parameter(ORG_ID, rmqOperatorClientConfigProperties.getOrgId())
                .build();
    }

    public DefaultCredentialsRefreshService defaultCredentialsRefreshService() {
        log.info("initializing defaultCredentialsRefreshService ....");
        return new DefaultCredentialsRefreshService.DefaultCredentialsRefreshServiceBuilder().scheduler(null)
                .refreshDelayStrategy(duration -> Duration.ofSeconds((long) ((double) duration.getSeconds() * 0.5)))
                .approachingExpirationStrategy(ttl -> true).build();
    }


}
