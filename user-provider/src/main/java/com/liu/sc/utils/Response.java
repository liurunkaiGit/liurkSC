package com.liu.sc.utils;

import com.liu.sc.utils.enumUtils.ResponseCode;
import lombok.Data;

@Data
public class Response {

    private Integer status;
    private String message;
    private Object result;

    public static Response success(Object object){
        Response response = new Response();
        response.setStatus(ResponseCode.SUCCESS.getStatus());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        response.setResult(object);
        return response;
    }

    public static Response fail(){
        Response response = new Response();
        response.setStatus(ResponseCode.FAIL.getStatus());
        response.setMessage(ResponseCode.FAIL.getMessage());
        return response;
    }

    public static Response fail(Integer status, String message){
        Response response = new Response();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }
}
