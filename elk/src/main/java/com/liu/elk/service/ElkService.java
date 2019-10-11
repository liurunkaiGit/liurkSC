package com.liu.elk.service;

import com.liu.elk.bean.EsBeanGood;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/9/11 17:19
 */
public interface ElkService {
    long count();

    EsBeanGood save(EsBeanGood commodity);

    void delete(EsBeanGood commodity);

    Iterable<EsBeanGood> getAll();

    List<EsBeanGood> getByName(String name);

    Page<EsBeanGood> pageQuery(Integer pageNo, Integer pageSize, String kw);
}
