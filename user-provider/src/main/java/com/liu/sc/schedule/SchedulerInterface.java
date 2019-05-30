package com.liu.sc.schedule;

import com.liu.sc.bean.Cron;
import com.liu.sc.service.CronService;
import com.liu.sc.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description: springboot基于接口的方式实现定时
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 16:13
 */
@Slf4j
@Configuration
@Component
@EnableScheduling
@EnableAsync
public class SchedulerInterface implements SchedulingConfigurer {

    @Autowired
    private CronService cronService;

    @Async
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
            //1.添加任务内容
            () -> log.info("currentThread is " + Thread.currentThread().getName() + "currentTime is {}" + LocalDateTime.now().toLocalTime()),
            //2.设置执行周期
            triggerContext -> {
                //从数据库获取执行周期
                Cron cron = cronService.getCronByType(1);
                //合法性校验
                //返回执行周期
                return new CronTrigger(cron.getCron()).nextExecutionTime(triggerContext);
            }
        );
    }
}
