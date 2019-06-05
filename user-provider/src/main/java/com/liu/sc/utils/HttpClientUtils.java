package com.liu.sc.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用接口工具类
 */
public class HttpClientUtils {

    /**
     * get请求
     */
    public static Map<String,Object> doGet(String url) throws Exception{
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        //httpGet.addHeader("Content-type", "application/json");
        //通过请求对象获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //获取结果实体
        //判断网络连接状态码是否正常
        Map<String, Object> map = new HashMap<String, Object>();
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String result = EntityUtils.toString(response.getEntity(),"UTF-8");
            //将json格式的result转换为map对象
            map = (Map<String, Object>) JSON.parse(result);
        }
        //释放连接
        response.close();
        return map;
    }

    /**
     * post请求
     * @param url 请求地址
     * @param param 请求参数：{ "userName":"admin", "password":"123456" }
     * @return
     */
    public static Map<String,Object> doPost(String url,Map<String,String> param) throws Exception{
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //添加参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if(param != null){
            for (Map.Entry<String, String> entry : param.entrySet()){
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
        //设置header信息
        httpPost.setHeader("Content-Type","application/json");
        //执行请求操作
        CloseableHttpResponse response = httpClient.execute(httpPost);
        //获取结果实体，判断网络连接状态码是否正常（0-200都正常）
        Map<String, Object> map = new HashMap<String, Object>();
        if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
            String result = EntityUtils.toString(response.getEntity(),"UTF-8");
            //将result转换为map
            map = (Map<String,Object>)JSON.parse(result);
        }
        response.close();
        return map;
    }

}
