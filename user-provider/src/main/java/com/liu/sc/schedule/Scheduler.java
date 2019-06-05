package com.liu.sc.schedule;

import com.liu.sc.bean.User;
import com.liu.sc.service.UserService;
import com.liu.sc.utils.contants.ScheduleContant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Description: springBoot基于注解的方式实现定时任务
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 15:52
 * 使用注解的方式需要在application的启动类上或者当前类上加@EnableScheduling注解
 */
@Component
@Slf4j
@Configuration
public class Scheduler {

    @Autowired
    private UserService userService;

    //@Scheduled(cron = "0/2 * * * * *") 也可以使用表达式
    /*@Scheduled(fixedDelay = 2000)
    public void findUserList(){
        User user = new User();
        List<User> userList = this.userService.findUserList(user);
        log.info("当前时间是{},用户量是{}",new Date(),userList.size());
    }*/
}
