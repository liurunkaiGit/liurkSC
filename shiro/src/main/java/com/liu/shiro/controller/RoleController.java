package com.liu.shiro.controller;

import com.liu.shiro.utils.response.Response;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 角色测试
 * @author: liurunkai
 * @Date: 2020/1/7 14:51
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    /**
     * 管理员角色测试接口
     *
     * @return
     */
    @GetMapping(value = "/getAdminInfo")
    @RequiresRoles("ADMIN")
    public Response getAdminInfo() {
        return Response.success("这里是只有管理员角色才能访问的接口");
    }

    /**
     * 用户角色测试接口
     *
     * @return
     */
    @GetMapping(value = "/getUserInfo")
    @RequiresRoles(value = {"USER"})
    public Response getUserInfo() {
        return Response.success("这里是只有用户角色才能访问的接口");
    }

    /**
     * 角色测试接口
     *
     * @return
     */
    @GetMapping(value = "/getRoleInfo")
    @RequiresRoles(value = {"ADMIN", "USER"}, logical = Logical.OR)
    @RequiresUser
    public Response getRoleInfo() {
        return Response.success("这里是只要有管理员角色和用户角色就能访问的接口");
    }

    /**
     * 退出
     *
     * @return
     */
    @GetMapping(value = "logout")
    @RequiresUser
    public Response logout() {
        return Response.success("退出");
    }
}
