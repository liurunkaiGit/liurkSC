package com.liu.shiro.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: spring获取上下文工具类
 * @author: liurunkai
 * @Date: 2020/1/7 10:53
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * spring在bean初始化后会判断是不是ApplicationContextAware的子类
     * 如果该类是setApplicationContext()方法，会将容器中ApplicationContext作为参数传入进去
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = applicationContext;
    }

    /**
     * 通过制定的beanName返回Bean
     *
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }
}
