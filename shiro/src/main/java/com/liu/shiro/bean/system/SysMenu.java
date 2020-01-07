package com.liu.shiro.bean.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 11:41
 */
@Data
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    private Long menuId;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String perms;
}
