package com.liu.sc.schedule.dynamicSchedule;

import com.liu.sc.bean.Cron;
import com.liu.sc.bean.User;
import com.liu.sc.service.CronService;
import com.liu.sc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/6/12 10:39
 */
@Component
@Configuration
public class DynamicScheduleTask implements SchedulingConfigurer {

    @Autowired
    private CronService cronService;
    @Autowired
    private UserService userService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                Long balance = 1L;
                user.setBalance(BigDecimal.valueOf(balance));
                user.setAge(22);
                user.setUserName("111");
                user.setName("222");
                userService.addUser(user);
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                Cron cron = cronService.getCronByType(1);
                if("".equals(cron.getCron())||cron.getCron()==null){
                    return null;
                }
                //定时任务触发,可修改定时任务的执行周期
                CronTrigger trigger=new CronTrigger(cron.getCron());
                Date nextExecDate= trigger.nextExecutionTime(triggerContext);
                return nextExecDate;
            }
        });
    }
}
