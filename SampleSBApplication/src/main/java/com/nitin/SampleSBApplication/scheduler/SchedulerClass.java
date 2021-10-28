package com.nitin.SampleSBApplication.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerClass {
    private static Logger logger = LoggerFactory.getLogger(SchedulerClass.class);

    @Scheduled(fixedRate = 10000, initialDelay = 3000)
    public void fixedRateScheduler() {
        logger.info("Cron job called..");
    }
}
