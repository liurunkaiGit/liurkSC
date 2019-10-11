package com.liu.elk.bean;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/9/17 10:59
 */
@Data
/**
 * 这里定义了EsBeanGood实例，表示商品，在es 6.x版本中，不建议使用type，而且在7.x中彻底废弃type，所以这里只指定了indexName，没有指定type
 * 这里一个EsBeanGood代表一个商品，同时代表一条索引记录
 * 类比关系型数据库的话，index相当于表，Document相当于记录
 */
@Document(indexName = "esBeanGood")
public class EsBeanGood implements Serializable {
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 类别，种类，类型
     */
    private String category;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 现货，存货，库存
     */
    private Integer stock;
}
