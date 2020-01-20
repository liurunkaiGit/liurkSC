package com.liu.distributedSecurity.uaa.controller;

import com.liu.distributedSecurity.uaa.bean.SysMenu;
import com.liu.distributedSecurity.uaa.bean.SysRole;
import com.liu.distributedSecurity.uaa.bean.SysRoleMenu;
import com.liu.distributedSecurity.uaa.bean.SysUser;
import com.liu.distributedSecurity.uaa.service.SysMenuService;
import com.liu.distributedSecurity.uaa.service.SysRoleMenuService;
import com.liu.distributedSecurity.uaa.service.SysRoleService;
import com.liu.distributedSecurity.uaa.service.SysUserService;
import com.liu.security.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 权限测试
 * @author: liurunkai
 * @Date: 2020/1/7 15:21
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 获取用户信息集合
     *
     * @return
     */
    @GetMapping(value = "/selectUserList")
    public Response selectUserList() {
        List<SysUser> list = this.sysUserService.list();
        return Response.success(list);
    }

    /**
     * 获取角色信息集合
     *
     * @return
     */
    @GetMapping(value = "/selectRoleList")
    public Response selectRoleList() {
        List<SysRole> list = this.sysRoleService.list();
        return Response.success(list);
    }

    /**
     * 获取权限信息集合
     *
     * @return
     */
    @GetMapping(value = "/selectMenuList")
    public Response selectMenuList() {
        List<SysMenu> list = this.sysMenuService.list();
        return Response.success(list);
    }

    /**
     * 获取所有数据
     *
     * @return
     */
    @GetMapping(value = "/selectAllData")
    public Response selectAllData() {
        List<SysMenu> menuList = this.sysMenuService.list();
        List<SysUser> userList = this.sysUserService.list();
        List<SysRole> roleList = this.sysRoleService.list();
        List<Object> list = new ArrayList<>();
        list.add(menuList);
        list.add(userList);
        list.add(roleList);
        return Response.success(list);
    }

    @GetMapping("/addMenu")
    public Response addMenu() {
        // 添加管理员角色权限
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setMenuId(4L);
        sysRoleMenu.setRoleId(1L);
        this.sysRoleMenuService.save(sysRoleMenu);
        // 清除缓存
        return Response.success("权限添加成功");
    }
}
