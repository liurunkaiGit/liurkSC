package com.liu.sc.service.impl;

import com.liu.sc.bean.Cron;
import com.liu.sc.dao.CronDao;
import com.liu.sc.service.CronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 16:18
 */
@Service
@Transactional
public class CronServiceImpl implements CronService {

    @Autowired
    private CronDao cronDao;

    @Override
    public Cron getCronByType(Integer type) {
        return this.cronDao.getCronByType(type);
    }
}
