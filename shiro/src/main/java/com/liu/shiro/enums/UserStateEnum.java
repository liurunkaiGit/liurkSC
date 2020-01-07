package com.liu.shiro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 用户状态枚举类
 * @author: liurunkai
 * @Date: 2020/1/7 13:55
 */
@Getter
@AllArgsConstructor
public enum UserStateEnum {

    NORMAL(0, "正常"),
    PROHIBIT(1, "禁用");

    private Integer state;
    private String message;
}
