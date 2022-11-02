package com.nitin.samplerabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class SampleRabbitmqConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleRabbitmqConsumerApplication.class, args);
	}

}
