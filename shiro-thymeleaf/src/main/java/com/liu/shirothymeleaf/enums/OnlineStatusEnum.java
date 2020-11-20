package com.liu.shirothymeleaf.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/9/24 15:51
 */
@Getter
@AllArgsConstructor
public enum OnlineStatusEnum {
    /**
     * 用户状态
     */
    on_line("在线"), off_line("离线");

    private final String info;
}
