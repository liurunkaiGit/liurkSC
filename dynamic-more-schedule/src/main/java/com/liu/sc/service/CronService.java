package com.liu.sc.service;

import com.liu.sc.bean.Cron;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 16:17
 */
public interface CronService {

    Cron getCronByType(Integer type);
}
