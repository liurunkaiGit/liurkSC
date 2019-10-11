package com.liu.elk.controller;

import com.liu.elk.bean.EsBeanGood;
import com.liu.elk.query.EsBeanGoodQuery;
import com.liu.elk.service.ElkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: elk控制层
 * @Author:W_LIURUNKAI
 * @Date:2019/9/11 17:18
 */
@Slf4j
@RestController
@RequestMapping("/es")
public class ElkController {

    @Autowired
    private ElkService elkService;

    @PostMapping(value = "/add",consumes = "application/json")
    public void insertEsBeanGood(@RequestBody EsBeanGood esBeanGood){
        this.elkService.save(esBeanGood);
    }

    @PostMapping(value = "searchByPage",consumes = "application/json")
    public Page<EsBeanGood> searchByPage(@RequestBody EsBeanGoodQuery esBeanGoodQuery){
        log.info("总记录数是{}",this.elkService.count());
        Page<EsBeanGood> esBeanGoods = this.elkService.pageQuery(esBeanGoodQuery.getPage(), esBeanGoodQuery.getPageSize(), esBeanGoodQuery.getName());
        return esBeanGoods;
    }

    @PostMapping(value = "searchAll",consumes = "application/json")
    public Iterable<EsBeanGood> searchByPage(){
        Iterable<EsBeanGood> all = this.elkService.getAll();
        return all;
    }

    @GetMapping(value = "deleteById")
    public void searchByPage(Long id){
        EsBeanGood esBeanGood = new EsBeanGood();
        esBeanGood.setId(id);
        this.elkService.delete(esBeanGood);
    }

}
