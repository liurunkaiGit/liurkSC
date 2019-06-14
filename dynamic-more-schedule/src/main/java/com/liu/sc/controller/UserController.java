package com.liu.sc.controller;

import com.liu.sc.bean.Cron;
import com.liu.sc.bean.User;
import com.liu.sc.service.CronService;
import com.liu.sc.service.UserService;
import com.liu.sc.utils.DefaultSchedulingConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private CronService cronService;
    @Autowired
    private DefaultSchedulingConfigurer defaultSchedulingConfigurer;
    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "/getUserById/{id}")
    public User getUserById(@PathVariable Long id){
        try {
            User user = this.userService.getUserById(id);
            log.info("user is {}",user);
            return user;
        } catch (Exception e) {
            log.error("发生异常{}",e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/testDynamicSchedule")
    public void testDynamicSchedule(){
        User user = new User();
        Long balance = 1L;
        user.setBalance(BigDecimal.valueOf(balance));
        user.setAge(11);
        user.setUserName("111");
        user.setName("111");
        defaultSchedulingConfigurer.addTriggerTask(String.valueOf(1),
                new TriggerTask(new Runnable() {
                    @Override
                    public void run() {
                        User user1 = userService.addUser(user);
                        log.info("userId is {}", user1.getId());
                    }
                }, new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        Cron cron1 = cronService.getCronByType(1);
                        // 验证cron表达式
                        if("".equals(cron1.getCron()) || cron1.getCron() == null){
                            throw new SchedulingException("cron is null");
                        }
                        CronExpression.isValidExpression(cron1.getCron());
                        //定时任务触发，可修改定时任务的执行周期
                        CronTrigger cronTrigger = new CronTrigger(cron1.getCron());
                        Date nextExecutionTime = cronTrigger.nextExecutionTime(triggerContext);
                        return nextExecutionTime;
                    }
                }));
    }

    @GetMapping("/testDynamicSchedule2")
    public void testDynamicSchedule2(){
        Cron cron2 = cronService.getCronByType(2);
        User user2 = new User();
        Long balance2 = 2L;
        user2.setBalance(BigDecimal.valueOf(balance2));
        user2.setAge(22);
        user2.setUserName("222");
        user2.setName("222");
        defaultSchedulingConfigurer.addTriggerTask(String.valueOf(2),
                new TriggerTask(new Runnable() {
                    @Override
                    public void run() {
                        User user = userService.addUser(user2);
                        log.info("userId is {}",user.getId());
                    }
                }, new CronTrigger(cron2.getCron())));
    }

    @GetMapping("/testCancelSchedule")
    public void testCancelSchedule(){
        Set<String> ids = defaultSchedulingConfigurer.taskIds();
        log.info("ids is {}",ids);
        defaultSchedulingConfigurer.cancelTriggerTask(String.valueOf(1));
    }

    public static void main(String[] args) {
        boolean b = CronExpression.isValidExpression("0/5 * * * * ?");
        System.out.println(b);
    }
}
