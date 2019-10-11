package com.liu.sc.test.stream;

import com.liu.sc.common.BaseTest;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/7/2 11:24
 */
public class testLambda extends BaseTest {
    @Test
    public void testFile() throws Exception{
        //列举目录中的所有文件
        List<Path> filesList = Files.list(Paths.get(".")).collect(Collectors.toList());
        System.out.println(filesList);
        //获取目标路径下所有文件
        List<Path> targetPathFilesList = Files.list(Paths.get(".")).filter(Files::isDirectory).collect(Collectors.toList());
        System.out.println(targetPathFilesList);
    }

    @Test
    public void test(){
        System.out.println(1);
    }
}
