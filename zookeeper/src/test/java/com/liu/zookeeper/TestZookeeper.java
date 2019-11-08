package com.liu.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Description: zookeeper客户端连接服务器测试demo
 * @Author:W_LIURUNKAI
 * @Date:2019/10/26 15:42
 */
@Slf4j
public class TestZookeeper {
    private ZooKeeper zooKeeper;

    /**
     * 初始化zookeeper客户端
     */
    @BeforeAll
    public void initZookeeperClient() {
        //String connectString = "ip:2181"; 连接单机版
        //连接集群，多个之间用逗号分割，中间不能有空格
        String connectString = "ip:2181,ip2:2181,ip3:2181";
        int sessionTimeout = 2000;
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

    /**
     * 创建节点
     */
    @Test
    public void createNode() {
        try {
            String zNode = zooKeeper.create("/testCreateNode", "hello zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        } catch (Exception e) {
            log.error("创建zNode失败{}", e);
            new RuntimeException("创建zNode失败");
        }
    }

    /**
     * 获取节点
     */
    @Test
    public void getNode() {
        try {
            // 第二个参数设置成true表示开启监听,但是需要在process方法里面执行次监听事件，循环输出节点
            List<String> children = zooKeeper.getChildren("/", false);
            children.forEach(child -> log.info("节点{}", child));
        } catch (Exception e) {
            log.error("获取节点失败{}", e);
            new RuntimeException("获取节点失败");
        }
    }

    /**
     * 判断节点是否存在
     */
    @Test
    public void isExist() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/aa", false);
        log.info("{}", exists);
    }
}
