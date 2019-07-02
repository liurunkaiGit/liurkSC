package com.liu.sc.controller;

import com.liu.sc.bean.User;
import com.liu.sc.common.BaseTest;
import com.liu.sc.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class UserControllerTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFile() throws Exception{
        List<User> userList = userService.findUserList(new User());
        System.out.println(userList);
        //列举目录中的所有文件
        List<Path> filesList = Files.list(Paths.get(".")).collect(Collectors.toList());
        System.out.println(filesList);
        //获取目标路径下所有文件
        List<Path> targetPathFilesList = Files.list(Paths.get(".")).filter(Files::isDirectory).collect(Collectors.toList());
        System.out.println(targetPathFilesList);
    }

}