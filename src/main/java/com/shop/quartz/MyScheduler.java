package com.shop.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @PACKAGE_NAME: com.shop.quartz
 * @author: XIA
 * @NAME: MyScheduler
 * @DATE: 2020/1/23
 **/
@Component
public class MyScheduler {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public void scheduler() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        startJob(scheduler);
    }

    public void startJob(Scheduler scheduler) throws SchedulerException {
        //创建实例
        JobDetail jobDetail = JobBuilder.newJob(Job.class).withIdentity("job1","group1").build();
        //执行时间 秒 分 时 日 月 周 年    ?：匹配该域的任意值。月份的天和周的天互相冲突，必须将其中一个设置为?
        // 每一分钟的00秒启动
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 */1 * * * ?");
        //创建触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
}
