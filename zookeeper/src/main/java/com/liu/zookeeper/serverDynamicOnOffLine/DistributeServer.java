package com.liu.zookeeper.serverDynamicOnOffLine;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Description: 服务器动态上下线demo
 * @Author:W_LIURUNKAI
 * @Date:2019/10/26 20:55
 */
@Slf4j
public class DistributeServer {

    String connectString = "ip:2181,ip2:2181,ip3:2181";
    int sessionTimeout = 2000;
    private ZooKeeper zooKeeper;

    public static void main(String[] args) {
        DistributeServer distributeServer = new DistributeServer();
        // 1. 连接服务器
        distributeServer.getConnect();
        // 2. 注册节点
        distributeServer.addZnode(args[0]);
        // 3. 执行相应的业务逻辑
        distributeServer.business();
    }

    private void business() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addZnode(String hostname) {
        try {
            zooKeeper.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            log.info("{} online", hostname);
        } catch (Exception e) {
            log.error("创建zNode失败{}", e);
            new RuntimeException("创建zNode失败");
        }
    }

    private void getConnect() {
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                }
            });
        } catch (IOException e) {
            log.error("连接服务器异常，error is {}", e);
            throw new RuntimeException("连接服务器异常");
        }
    }
}
