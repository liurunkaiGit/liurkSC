package com.liu.elk.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description: 配置文件取值
 * @Author:W_LIURUNKAI
 * @Date:2019/9/17 10:42
 */
@Data
@Component
@Configuration
public class ElkConfig {

    // es配置
    @Value("${spring.data.elasticsearch.cluster-name:es}")
    private String esClusterName;

    @Value("${spring.data.elasticsearch.cluster-nodes:localhost:9300}")
    private String esClusterNodes;
}
