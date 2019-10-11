package com.liu.sc.utils.enumUtils;

import lombok.Getter;

/**
 * @Description: 状态枚举类
 * @Author:W_LIURUNKAI
 * @Date:2019/8/21 10:36
 */
@Getter
public enum StatusEnum {

    DEL(0, "删除"),
    NODEL(1, "没有删除");

    private Integer status;
    private String message;

    StatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
