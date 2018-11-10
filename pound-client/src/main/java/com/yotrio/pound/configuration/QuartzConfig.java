package com.yotrio.pound.configuration;

import com.yotrio.pound.tasks.SyncCompanyTask;
import com.yotrio.pound.tasks.SyncGoodsTask;
import com.yotrio.pound.tasks.SyncOrganizationTask;
import com.yotrio.pound.tasks.TaskQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    /**
     * 定时任务执行时间/分钟
     */
    private static final int INTERVAL_IN_SECONDS_TASK = 5 * 60;

    /**
     * 同步任务执行时间/小时
     */
    private static final int INTERVAL_IN_HOURS_SYNC = 1 ;

    /**
     * 执行定时任务
     * @return
     */
    @Bean
    public JobDetail taskQuartzDetail() {
        return JobBuilder.newJob(TaskQuartz.class).withIdentity("taskQuartz").storeDurably().build();
    }

    /**
     * 执行定时任务
     * @return
     */
    @Bean
    public Trigger testQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                //5分钟同步一次
                .withIntervalInSeconds(INTERVAL_IN_SECONDS_TASK)  //设置时间周期单位秒
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(taskQuartzDetail())
                .withIdentity("taskQuartz")
                .withSchedule(scheduleBuilder)
                .build();
    }

    /**
     * 同步公司
     *
     * @return
     */
    @Bean
    public JobDetail syncCompanyQuartzDetail() {
        return JobBuilder.newJob(SyncCompanyTask.class).withIdentity("syncCompanyTask").storeDurably().build();
    }

    /**
     * 同步公司
     *
     * @return
     */
    @Bean
    public Trigger syncCompanyQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                //6小时执行一次
                .withIntervalInHours(INTERVAL_IN_HOURS_SYNC)  //设置时间周期单位小时
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(syncCompanyQuartzDetail())
                .withIdentity("syncCompanyTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

    /**
     * 同步组织
     *
     * @return
     */
    @Bean
    public JobDetail syncOrganizationQuartzDetail() {
        return JobBuilder.newJob(SyncOrganizationTask.class).withIdentity("syncOrganizationTask").storeDurably().build();
    }

    /**
     * 同步组织
     *
     * @return
     */
    @Bean
    public Trigger syncOrganizationQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                //6小时执行一次
                .withIntervalInHours(INTERVAL_IN_HOURS_SYNC)  //设置时间周期单位小时
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(syncOrganizationQuartzDetail())
                .withIdentity("syncOrganizationTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

    /**
     * 同步物料
     *
     * @return
     */
    @Bean
    public JobDetail syncGoodsQuartzDetail() {
        return JobBuilder.newJob(SyncGoodsTask.class).withIdentity("syncGoodsTask").storeDurably().build();
    }

    /**
     * 同步物料
     *
     * @return
     */
    @Bean
    public Trigger syncGoodsQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                //6小时执行一次
                .withIntervalInHours(INTERVAL_IN_HOURS_SYNC)  //设置时间周期单位小时
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(syncGoodsQuartzDetail())
                .withIdentity("syncGoodsTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}

