package com.nitin.SampleSBApplication.runner;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
Spring boot provides two runner interfaces (CommandLineRunner, ApplicationRunner) to run a piece of code just after starting a spring Boot application
 */

@Slf4j
@Component
public class SampleCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("** Application started with command-line arguments: {} . ", Arrays.toString(args));
    }
}
