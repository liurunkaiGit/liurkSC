package com.log.log4j2.listener;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * @Description: 新建一个监听类，读取yml中的属性值，通过MDC设置到log4j2的上下文
 * @Author:W_LIURUNKAI
 * @Date:2019/9/26 19:45
 */
@Slf4j
public class ApplicationStartedEventListener implements GenericApplicationListener {

    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 10;

    private static Class<?>[] EVENT_TYPES = {ApplicationStartingEvent.class,
            ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class,
            ContextClosedEvent.class, ApplicationFailedEvent.class};

    private static Class<?>[] SOURCE_TYPES = {SpringApplication.class,
            ApplicationContext.class};

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {

            ConfigurableEnvironment envi = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();
            MutablePropertySources mps = envi.getPropertySources();

            PropertySource<?> ps = mps.get("configurationProperties");

            if (ps != null && ps.containsProperty("server.servlet.contextpath")) {
                String contextpath = (String) ps.getProperty("server.servlet.contextpath");
                System.out.println(contextpath);
                MDC.put("contextpath", contextpath);
            }
            /*configurationProperties
            applicationConfig: [classpath:/application.yml]*/
            // 如果需要获取其它的，继续添加
            /*if (ps != null && ps.containsProperty("logging.file")) {
                String fileName = (String) ps.getProperty("logging.file");
                //System.out.println(kafkaUrl);
                MDC.put("fileName", fileName);
            }*/

        }
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }

}
