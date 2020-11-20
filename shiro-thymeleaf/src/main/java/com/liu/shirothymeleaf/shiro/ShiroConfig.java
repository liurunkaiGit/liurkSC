package com.liu.shirothymeleaf.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.liu.shirothymeleaf.shiro.filter.kickout.KickoutSessionControlFilter;
import com.liu.shirothymeleaf.shiro.filter.online.SyncOnlineSessionFilter;
import com.liu.shirothymeleaf.shiro.session.OnlineSessionDAO;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: Shiro配置类
 * 需要创建3个对象：ShiroFilterFactoryBean、DefaultWebSecurityManager、Realm(需要自定义Realm：ShiroRealm)
 * @author: liurunkai
 * @Date: 2020/1/13 18:17
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        //设置安全管理器
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // 添加shiro内置过滤器
//        /**
//         * shiro内置过滤器，可以实现权限相关的拦截。常用的过滤器：
//         * anon：无需认证(登录)就可以访问
//         * authc：必须认证才可以访问
//         * user：如果使用remeberMe的功能可以直接访问
//         * perms：该资源必须得到资源权限才可以访问
//         * role：该资源必须得到角色权限才可以访问
//         */
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        // 设置某些请求不拦截，即不需要经过认证(登录)也可以能访问：anon
//        filterChainDefinitionMap.put("/test", "anon");
//        filterChainDefinitionMap.put("/login/login", "anon");
//
//        // 授权过滤器，注意：当授权拦截后，shiro会自动跳转到未授权页面
//        filterChainDefinitionMap.put("/user/add", "perms[sys:user:add]");
//        filterChainDefinitionMap.put("/user/update", "perms[sys:user:update]");
//
//        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
////        filters.put("syncOnlineSession", onlineSessionFilter());
//        // 限制同一账号同时在线个数
//        filters.put("kickout", kickoutSessionFilter());
//        // 设置未授权提示页面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/login/unauth");
//
//        //如果没有登录默认跳转到login.jsp页面，修改默认跳转路径为login.html
//        shiroFilterFactoryBean.setLoginUrl("/login/toLogin");
//        // 默认拦截所有请求，必须经过认证(登录)才能访问：authc
//        // 注意：这个拦截所有请求的这一行必须放到最后(即放到设置不拦截请求的后面，否则即便设置了某些请求不用认证，也不生效)
//        filterChainDefinitionMap.put("/**", "authc");
////        filterChainDefinitionMap.put("/**", "user,kickout,onlineSession,syncOnlineSession");
////        filterChainDefinitionMap.put("/login", "kickout,anon");
//        //其他资源都需要认证  authc 表示需要认证才能进行访问 user表示配置记住我或认证通过可以访问的地址
////        filterChainDefinitionMap.put("/**", "kickout,user");
////        filterChainDefinitionMap.put("/**", "kickout,user");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl("/login/toLogin");
        // 权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/login/unauth");
        // Shiro连接约束配置，即过滤链的定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 对静态资源设置匿名访问
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/ruoyi.png**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/ajax/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/ruoyi/**", "anon");
        filterChainDefinitionMap.put("/captcha/captchaImage**", "anon");
        // 退出 logout地址，shiro去清除session
        filterChainDefinitionMap.put("/logout", "logout");
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/login", "anon,captchaValidate");
        // 注册相关
        filterChainDefinitionMap.put("/register", "anon,captchaValidate");
        // 系统权限列表
        // filterChainDefinitionMap.putAll(SpringUtils.getBean(IMenuService.class).selectPermsAll());

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("onlineSession", onlineSessionFilter());
//        filters.put("syncOnlineSession", syncOnlineSessionFilter());
//        filters.put("captchaValidate", captchaValidateFilter());
        filters.put("kickout", kickoutSessionFilter());
        // 注销成功，则跳转到指定页面
//        filters.put("logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // 所有请求需要认证
        filterChainDefinitionMap.put("/**", "user,kickout,onlineSession,syncOnlineSession");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public KickoutSessionControlFilter kickoutSessionFilter() {
        KickoutSessionControlFilter kickoutSessionFilter = new KickoutSessionControlFilter();
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        //这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
        //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
        kickoutSessionFilter.setCacheManager(ehCacheManager());
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionFilter.setSessionManager(sessionManager());
        // 同一个用户最大的会话数，默认-1无限制；比如2的意思是同一个用户允许最多同时两个人登录
        kickoutSessionFilter.setMaxSession(1);
        // 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序,false:踢出最早登录的，true：踢出最后登录的
        kickoutSessionFilter.setKickoutAfter(false);
        // 被踢出后重定向到的地址；
        kickoutSessionFilter.setKickoutUrl("/login/toLogin?kickout=1");
        return kickoutSessionFilter;
    }

    /**
     * 创建DefaultWebSecurityManager
     *
     * @param shiroRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm
        defaultWebSecurityManager.setRealm(shiroRealm);
        // session管理器
        defaultWebSecurityManager.setSessionManager(sessionManager());
        return defaultWebSecurityManager;
    }

    /**
     * 创建Realm
     *
     * @return
     */
    @Bean(name = "shiroRealm")
    public ShiroRealm getRealm() {
        return new ShiroRealm();
    }

    /**
     * thymeleaf模板引擎和shiro框架整合
     *
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 自定义在线用户过滤器
     */
    @Bean
    public SyncOnlineSessionFilter onlineSessionFilter() {
        return new SyncOnlineSessionFilter();
    }

    /**
     * 自定义sessionDAO会话
     */
    @Bean
    public OnlineSessionDAO sessionDAO() {
        OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
        return sessionDAO;
    }

    /**
     * 会话管理器
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        // 加入缓存管理器
        manager.setCacheManager(ehCacheManager());
        // 设置全局session超时时间
        manager.setGlobalSessionTimeout(1000 * 60);
        // 定义要使用的无效的Session定时调度器
//        manager.setSessionValidationScheduler(SpringUtils.getBean(SpringSessionValidationScheduler.class));
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        //暂时设置为 5秒 用来测试
        manager.setSessionValidationInterval(5000);
        // 是否定时检查session
        manager.setSessionValidationSchedulerEnabled(true);
        // 自定义SessionDao
        manager.setSessionDAO(sessionDAO());
        // 自定义sessionFactory
//        manager.setSessionFactory(new OnlineSessionFactory());
        //是否开启删除无效的session对象  默认为true
        manager.setDeleteInvalidSessions(true);
        //是否开启定时调度器进行检测过期session 默认为true
        manager.setSessionValidationSchedulerEnabled(true);
        //取消url 后面的 JSESSIONID
        manager.setSessionIdUrlRewritingEnabled(false);
        return manager;
    }

    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager  = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return cacheManager;
    }

//    /**
//     * 缓存管理器 使用Ehcache实现
//     */
//    @Bean
//    public EhCacheManager getEhCacheManager()
//    {
//        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getCacheManager("ruoyi");
//        EhCacheManager em = new EhCacheManager();
//        if (cacheManager == null)
//        {
//            em.setCacheManager(new net.sf.ehcache.CacheManager(getCacheManagerConfigFileInputStream()));
//            return em;
//        }
//        else
//        {
//            em.setCacheManager(cacheManager);
//            return em;
//        }
//    }
//
//    /**
//     * 返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
//     */
//    protected InputStream getCacheManagerConfigFileInputStream() {
//        String configFile = "classpath:ehcache/ehcache-shiro.xml";
//        InputStream inputStream = null;
//        try {
//            inputStream = ResourceUtils.getInputStreamForPath(configFile);
//            byte[] b = IOUtils.toByteArray(inputStream);
//            InputStream in = new ByteArrayInputStream(b);
//            return in;
//        } catch (IOException e) {
//            throw new ConfigurationException(
//                    "Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
//        } finally {
//            IOUtils.closeQuietly(inputStream);
//        }
//    }
}
