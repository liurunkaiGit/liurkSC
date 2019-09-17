package com.liu.elk.service.impl;

import com.liu.elk.bean.EsBeanGood;
import com.liu.elk.dao.ElkDao;
import com.liu.elk.service.ElkService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: elk 业务层
 * @Author:W_LIURUNKAI
 * @Date:2019/9/11 17:19
 */
@Service
@Transactional
public class ElkServiceImpl implements ElkService {

    @Autowired
    private ElkDao elkDao;


    @Override
    public long count() {
        return elkDao.count();
    }

    @Override
    public EsBeanGood save(EsBeanGood commodity) {
        return elkDao.save(commodity);
    }

    @Override
    public void delete(EsBeanGood commodity) {
        elkDao.delete(commodity);
//        commodityRepository.deleteById(commodity.getSkuId());
    }

    @Override
    public Iterable<EsBeanGood> getAll() {
        return elkDao.findAll();
    }

    @Override
    public List<EsBeanGood> getByName(String name) {
        List<EsBeanGood> list = new ArrayList<>();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", name);
        Iterable<EsBeanGood> iterable = elkDao.search(matchQueryBuilder);
        iterable.forEach(e->list.add(e));
        return list;
    }

    @Override
    public Page<EsBeanGood> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("name", kw))
                .withPageable(PageRequest.of(pageNo, pageSize))
                .build();
        return elkDao.search(searchQuery);
    }
}
