package com.liu.shirothymeleaf.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 11:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 权限
     */
    private String perms;

}
