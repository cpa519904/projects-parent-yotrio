package com.yotrio.pound.configuration;

import com.yotrio.pound.tasks.TaskQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail teatQuartzDetail() {
        return JobBuilder.newJob(TaskQuartz.class).withIdentity("testQuartz").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5 * 60)  //设置时间周期单位秒
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
                .withIdentity("taskQuartz")
                .withSchedule(scheduleBuilder)
                .build();
    }
}

