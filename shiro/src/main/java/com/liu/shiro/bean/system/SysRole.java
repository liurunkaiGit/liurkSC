package com.liu.shiro.bean.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 11:41
 */
@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
}
