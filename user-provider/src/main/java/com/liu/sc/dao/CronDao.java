package com.liu.sc.dao;

import com.liu.sc.bean.Cron;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 16:28
 */
@Mapper
public interface CronDao {
    Cron getCronByType(Integer type);
}
