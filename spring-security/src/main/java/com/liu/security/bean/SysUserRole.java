package com.liu.security.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 11:43
 */
@Data
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;

}
