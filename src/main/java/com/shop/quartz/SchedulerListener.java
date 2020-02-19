package com.shop.quartz;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @PACKAGE_NAME: com.shop.quartz
 * @author: XIA
 * @NAME: SchedulerListener
 * @DATE: 2020/1/23
 **/
@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MyJobFactory myJobFactory;
    @Autowired
    private MyScheduler myScheduler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            myScheduler.scheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(myJobFactory);
        return schedulerFactoryBean;
    }
}
