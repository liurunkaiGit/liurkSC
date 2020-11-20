package com.liurk.shardingsphere.bean;

import lombok.Data;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/30 10:23
 */
@Data
public class User {

    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
}
