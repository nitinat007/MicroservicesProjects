package com.nitin.SampleSBApplication.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
/*
ApplicationRunner wraps the raw application arguments and exposes the ApplicationArguments interface
 */

@Slf4j
@Component
public class SampleApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("** This will expose application args using ApplicationArguments ");
    }
}
