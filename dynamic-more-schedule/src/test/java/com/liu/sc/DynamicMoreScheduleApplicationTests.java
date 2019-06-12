package com.liu.sc;

import com.liu.sc.bean.Cron;
import com.liu.sc.bean.User;
import com.liu.sc.service.CronService;
import com.liu.sc.service.UserService;
import com.liu.sc.utils.DefaultSchedulingConfigurer;
import com.liu.sc.utils.DynamicScheduleTaskUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicMoreScheduleApplicationTests {

    @Autowired
    private CronService cronService;
    @Autowired
    private DefaultSchedulingConfigurer defaultSchedulingConfigurer;
    @Autowired
    private UserService userService;

}
