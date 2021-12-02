package com.nitin.SampleSBApplication.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
@Slf4j
public class CustomConfiguration {


    //exposing the property to be used globally
    @Bean(name="customPropBean")
    public Properties setProperties(){
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("customFolder/custom.properties"));
            //properties.load(new FileReader("src/main/resources/customFolder/custom.properties")); //this also works
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return properties;
    }

}
