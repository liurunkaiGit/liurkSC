package com.liu.sc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liu.sc.utils.HttpClientUtils;
import com.liu.sc.utils.Response;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/6/4 16:06
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Value("${es.url:http://127.0.0.1:9200}")
    public String esSearchPath;

    public static final String ES_SEARCH_PATH = "/log-*/_search";

    @ResponseBody
    @PostMapping(value = "/getLogFromES",consumes = "application/json")
    public Map<String, Object> getLogFromES() throws Exception{
        Map<String, Object> map = HttpClientUtils.doGet(esSearchPath.concat(ES_SEARCH_PATH));
        return map;
    }

}
