package com.liu.zookeeper.controller;

import com.liu.zookeeper.utils.ZookeeperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

/**
 * @Description: zookeeper使用
 * @Author:W_LIURUNKAI
 * @Date:2019/10/26 16:00
 */
@Slf4j
@RestController
@RequestMapping("/zookeeper")
public class ZookeeperController {

    @Autowired
    private ZookeeperUtil zookeeperUtil;

    @GetMapping(value = "/createNode")
    public void createNode(){

    }

}
