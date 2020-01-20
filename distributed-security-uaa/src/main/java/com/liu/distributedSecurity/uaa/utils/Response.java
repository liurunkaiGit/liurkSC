package com.liu.security.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description: 返回实体类
 * @author: liurunkai
 * @Date: 2020/1/7 10:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Integer status;
    private String message;
    private Object result;

    public Response(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static Response success() {
        return new Response(ResponseEnum.SUCCESS.getStatus(), ResponseEnum.SUCCESS.getMessage());
    }

    public static Response success(String message) {
        return new Response(ResponseEnum.SUCCESS.getStatus(), message);
    }

    public static Response success(Object result) {
        return new Response(ResponseEnum.SUCCESS.getStatus(), ResponseEnum.SUCCESS.getMessage(), result);
    }

    public static Response fail() {
        return new Response(ResponseEnum.FAIL.getStatus(), ResponseEnum.FAIL.getMessage());
    }

    public static Response fail(Integer status, String message) {
        return new Response(status, message);
    }

}

@Getter
@AllArgsConstructor
enum ResponseEnum {

    SUCCESS(200, "成功"),
    FAIL(500, "失败");

    private Integer status;
    private String message;
}
