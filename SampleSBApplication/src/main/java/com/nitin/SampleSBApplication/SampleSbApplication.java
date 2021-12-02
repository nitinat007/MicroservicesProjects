package com.nitin.SampleSBApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class SampleSbApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(SampleSbApplication.class);
    @Value("${person.name:defaultString}")
    private String name;
    @Value("${custom.prop.name:notFoundCustomPropName}")
    private String customName;

   // private Properties customProp;

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

    //exposing the property locally
    @PostConstruct
    public void loadPropertiesPostInit(){
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("customFolder/custom.properties"));
            //properties.load(new FileReader("src/main/resources/customFolder/custom.properties")); //this also works
            logger.info("** loaded custom.properties. Now reading ..");
            logger.info("** Property custom.prop.name={}",properties.getProperty("custom.prop.name"));
            logger.info(customName); //prints notFoundCustomPropName
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
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
