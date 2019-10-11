package com.liu.elk;

import com.liu.elk.bean.EsBeanGood;
import com.liu.elk.service.ElkService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/9/17 15:32
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElkTest {

    /**
     * 使用elasticsearch Repository的方式操作es
     */

    @Autowired
    private ElkService elkService;

    @Test
    public void testInsert() {
        EsBeanGood esBeanGood = new EsBeanGood();
        esBeanGood.setId(1L);
        esBeanGood.setName("原味切片面包（10片装）");
        esBeanGood.setCategory("101");
        esBeanGood.setPrice(BigDecimal.valueOf(1L));
        esBeanGood.setBrand("良品铺子");
        elkService.save(esBeanGood);

        esBeanGood = new EsBeanGood();
        esBeanGood.setId(3L);
        esBeanGood.setName("原味切片面包（6片装）");
        esBeanGood.setCategory("101");
        esBeanGood.setPrice(BigDecimal.valueOf(3L));
        esBeanGood.setBrand("良品铺子");
        elkService.save(esBeanGood);

        esBeanGood = new EsBeanGood();
        esBeanGood.setId(2L);
        esBeanGood.setName("元气吐司850g");
        esBeanGood.setCategory("101");
        esBeanGood.setPrice(BigDecimal.valueOf(2L));
        esBeanGood.setBrand("百草味");
        elkService.save(esBeanGood);

    }

    @Test
    public void testDelete() {
        EsBeanGood commodity = new EsBeanGood();
        commodity.setId(1L);
        elkService.delete(commodity);
    }

    @Test
    public void testGetAll() {
        Iterable<EsBeanGood> iterable = elkService.getAll();
        iterable.forEach(e->System.out.println(e.toString()));
    }

    @Test
    public void testGetByName() {
        List<EsBeanGood> list = elkService.getByName("面包");
        System.out.println(list);
    }

    @Test
    public void testPage() {
        Page<EsBeanGood> page = elkService.pageQuery(0, 10, "切片");
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        System.out.println(page.getContent());
    }

    // ==========================================================

    /**
     * 使用elasticsearchTemplate的方式操作elasticsearch
     */

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void add(){
        EsBeanGood esBeanGood = new EsBeanGood();
        esBeanGood.setId(1L);
        esBeanGood.setName("原味切片面包（10片装）");
        esBeanGood.setCategory("101");
        esBeanGood.setPrice(BigDecimal.valueOf(1L));
        esBeanGood.setBrand("良品铺子");

        IndexQuery indexQuery = new IndexQueryBuilder().withObject(esBeanGood).build();
        elasticsearchTemplate.index(indexQuery);
    }

    @Test
    public void testQuery(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", "吐司"))
                .build();
        List<EsBeanGood> esBeanGoods = elasticsearchTemplate.queryForList(searchQuery, EsBeanGood.class);
        esBeanGoods.stream().forEach(esBeanGood -> log.info("{}",esBeanGood));
    }

}
