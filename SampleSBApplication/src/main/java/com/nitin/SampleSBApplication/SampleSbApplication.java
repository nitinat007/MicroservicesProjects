package com.nitin.SampleSBApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SampleSbApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(SampleSbApplication.class);
    @Value("${person.name:defaultString}")
    private String name;

    public static void main(String[] args) {
        logger.info("This is info log");
        SpringApplication.run(SampleSbApplication.class, args);
    }

    /*
    CommandLineRunner or ApplicationRunner used to execute the code after the Spring Boot application started.
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Total arguments: " + args.length);
        for (String arg : args) {
            System.out.println("arg: " + arg);
        }
        System.out.println("Name: " + name);
    }
}


/* console O/P:
1. for "mvn spring-boot:run"
  	Total arguments: 0
	Name: defaultString

2. for "mvn spring-boot:run -Dspring-boot.run.arguments=--person.name=Test"
	Total arguments: 1
	arg: --person.name=Test
	Name: Test

Access http://localhost:9090/greet to get the REST API response



 */
