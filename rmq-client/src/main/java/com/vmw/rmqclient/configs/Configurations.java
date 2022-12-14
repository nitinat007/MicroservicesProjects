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
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

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
    @Autowired
    RmqOperatorClientConfigProperties rmqOperatorClientConfigProperties;

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

    public RabbitConnectionFactoryBean getConnectionFactoryBean() {
        log.info("initializing getConnectionFactoryBean ....");
        RabbitConnectionFactoryBean rabbitConnectionFactoryBean = new RabbitConnectionFactoryBean();

        if (rmqOperatorClientConfigProperties.getRmqPort() > 0)
            rabbitConnectionFactoryBean.setPort(rmqOperatorClientConfigProperties.getRmqPort());

        rabbitConnectionFactoryBean.setHost(rmqOperatorClientConfigProperties.getRmqHost());
        if (rmqOperatorClientConfigProperties.getClientId() != null) {
            rabbitConnectionFactoryBean.setCredentialsProvider(credentialsProvider());
            rabbitConnectionFactoryBean.setCredentialsRefreshService(defaultCredentialsRefreshService());
        } else {
            //added for local testing
            rabbitConnectionFactoryBean.setUsername("guest");
            rabbitConnectionFactoryBean.setPassword("guest");
        }

        log.info("rabbitConnectionFactoryBean :{}", rmqOperatorClientConfigProperties);
        rabbitConnectionFactoryBean.afterPropertiesSet();
        return rabbitConnectionFactoryBean;
    }

    public CredentialsProvider credentialsProvider() {
        log.info("initializing credentialsProvider ....");
        return new OAuth2ClientCredentialsGrantCredentialsProvider.OAuth2ClientCredentialsGrantCredentialsProviderBuilder()
                .tokenEndpointUri(rmqOperatorClientConfigProperties.getAuthenticationUrl())
                .clientId(rmqOperatorClientConfigProperties.getClientId())
                .clientSecret(rmqOperatorClientConfigProperties.getClientSecret())
                .grantType(rmqOperatorClientConfigProperties.getGrantType())
                .parameter(ORG_ID, rmqOperatorClientConfigProperties.getOrgId()).build();
    }

    public DefaultCredentialsRefreshService defaultCredentialsRefreshService() {
        log.info("initializing defaultCredentialsRefreshService ....");
        return new DefaultCredentialsRefreshService.DefaultCredentialsRefreshServiceBuilder().scheduler(null)
                .refreshDelayStrategy(duration -> Duration.ofSeconds((long) ((double) duration.getSeconds() * 0.5)))
                .approachingExpirationStrategy(ttl -> true).build();
    }


}
