package com.liu.zookeeper.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @Description: zookeeperUtil：初始化zookeeper客户端
 * @Author:W_LIURUNKAI
 * @Date:2019/10/26 15:58
 */
@Slf4j
public class ZookeeperUtil {

    String connectString = "ip:2181,ip2:2181,ip3:2181";
    int sessionTimeout = 2000;
    private ZooKeeper zooKeeper;

    @PostConstruct
    public void initZookeeperClient() {
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                }
            });
        } catch (IOException e) {
            log.error("连接zookeeper服务器异常{}", e);
            new RuntimeException("连接zookeeper服务器异常");
        }
    }
}
