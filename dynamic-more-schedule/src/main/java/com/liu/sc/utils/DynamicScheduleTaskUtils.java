package com.liu.sc.utils;

import com.liu.sc.bean.Cron;
import com.liu.sc.bean.User;
import com.liu.sc.service.CronService;
import com.liu.sc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/6/12 11:51
 */
@Component
@Configuration
public class DynamicScheduleTaskUtils {
    @Autowired
    private CronService cronService;
    @Autowired
    private UserService userService;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    private ScheduledFuture<?> future;

    public void startCron(Integer type, String cron, User user) {
        if (type == 1) {
            future = threadPoolTaskScheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    userService.addUser(user);
                }
            }, new CronTrigger(cron));
        } else if (type == 3) {
            future = threadPoolTaskScheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    userService.addUser(user);
                }
            }, new CronTrigger(cron));
        }
    }

    public void stopCron() {
        if (future != null) {
            future.cancel(true);
        }
        System.out.println("DynamicTask stopCron()");
    }

    public void updateCron() {

        stopCron();// 先停止，在开启.
        future = threadPoolTaskScheduler.schedule(new djRunnable(), new CronTrigger("*/10 * * * * *"));
        System.out.println("DynamicTask updateCron()");
    }


    private class djRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("处理堆积逻辑");
        }
    }

    private class failRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("处理失败逻辑");
        }
    }
}
