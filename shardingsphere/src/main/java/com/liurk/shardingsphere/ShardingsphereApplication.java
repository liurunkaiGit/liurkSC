package com.liurk.shardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * ShardingSphere实现分库分表和读写分离：
 *  需求：有db0和db1两个主数据库，sdb0和sdb1两个从数据库，每个数据库有user_0和user_1两张表
 *        分库分表：根据用户主键id%2取模的方式将数据落到不同的库，根据用户年龄age%2取模的方式将数据落到不同的表
 *        读写分离：向db0和db1主库里面写数据，从sdb0和sdb1两个从库读数据
 *                  需要实现主从同步，主从同步还要解决一系列问题，eg：主数据库挂了，从数据库可能会丢失数据
 * 1. 导入依赖：sharding-jdbc-spring-boot-starter、sharding-jdbc-spring-namespace
 * 2. 编写application.properties
 * 3. 测试
 */
@SpringBootApplication
public class ShardingsphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereApplication.class, args);
    }

}
