package com.liu.elk.dao;

import com.liu.elk.bean.EsBeanGood;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/9/11 17:20
 */
@Mapper
public interface ElkDao extends ElasticsearchRepository<EsBeanGood,Long> {

}
