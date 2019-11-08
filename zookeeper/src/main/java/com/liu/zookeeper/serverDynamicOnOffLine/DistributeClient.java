package com.liu.zookeeper.serverDynamicOnOffLine;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 服务器动态上下线demo
 * @Author:W_LIURUNKAI
 * @Date:2019/10/26 20:55
 */
@Slf4j
public class DistributeClient {

    String connectString = "ip:2181,ip2:2181,ip3:2181";
    int sessionTimeout = 2000;
    private ZooKeeper zooKeeper;

    public static void main(String[] args) {
        DistributeClient distributeClient = new DistributeClient();
        // 1. 连接服务器
        distributeClient.getConnect();
        // 2. 注册节点
        distributeClient.getChildren();
        // 3. 执行相应的业务逻辑
        distributeClient.business();
    }

    private void business() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getChildren() {
        try {
            List<String> children = zooKeeper.getChildren("/servers", true);
            List<byte[]> collect = children.stream()
                    .map(child -> {
                        try {
                            return zooKeeper.getData("/servers" + child, false, null);
                        } catch (Exception e) {
                            throw new RuntimeException("获取节点数据异常");
                        }
                    })
                    .collect(Collectors.toList());
            collect.forEach(c -> log.info("data is {}",c));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getConnect() {
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    getChildren();
                }
            });
        } catch (IOException e) {
            log.error("连接服务器异常，error is {}", e);
            throw new RuntimeException("连接服务器异常");
        }
    }
}
