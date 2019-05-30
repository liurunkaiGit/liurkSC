package com.liu.sc.Thread;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: springBoot中多线程的使用
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 15:14
 * springBoot是通过任务执行器(TaskExecutor)来实现多线程和并发编程，
 * 使用ThreadPoolTaskExecutor来创建一个基于线程池的TaskExecutor。在使用线程池的大部分情况下都是异步非阻塞的。
 * 我们配置注解@EnableAsync可以开启异步任务，然后在实际执行的方法上配置注解@Async上声明是异步任务.
 * 需要实现AsyncConfigure接口
 */
@Configuration
@EnableAsync
public class ThreadConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
