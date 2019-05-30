package com.liu.sc.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 16:24
 */
@Data
public class Cron implements Serializable {
    private Long id;
    private String cron;
    private Integer type;
}
