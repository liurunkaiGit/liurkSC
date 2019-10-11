package com.liu.sc.test.stream;

import com.liu.sc.bean.User;
import com.liu.sc.common.BaseTest;
import com.liu.sc.service.UserService;
import com.sun.org.apache.xml.internal.utils.StringComparable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.ognl.ASTList;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/6/6 17:27
 */
@Slf4j
public class testStream extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCount() {
        List<User> userList = this.userService.findUserList(new User());
        Integer count = 0;
        for (User user : userList) {
            if ("account1".equals(user.getUserName())) {
                count++;
            }
        }
        log.info("count is {}", count);

        long count1 = userList.stream()
                .filter(user -> "account1".equals(user.getUserName()))
                .count();
        log.info("count1 is {}", count1);
        System.out.println("================================");
        // filter只刻画出了stream，但没有产生新的集合，像filter这种只描述stream，最终不产生新集合的方法叫惰性求值方法
        // 而像count这样最终会从stream产生值的方法叫做及早求值方法
        //判断一个操作是惰性求值还是及早求值，就看它的返回值，如果返回Stream，就是惰性求值，否则就是及早求值
        Stream<User> userStream = userList.stream()
                .filter(user -> "account1".equals(user.getUserName()));

        //由于使用惰性求值，没有输出日志
        userList.stream().filter(user -> {
            log.info("惰性求值userName is {}", user.getUserName());
            return "account1".equals(user.getUserName());
        });
        //使用及早求值方法，输出userName
        long count2 = userList.stream().filter(user -> {
            log.info("及早求值userName is {}", user.getUserName());
            return "account1".equals(user.getUserName());
        }).count();
    }

    @Test
    public void testCollect() {
        List<String> collected = Stream.of("a", "b", "c")
                .collect(Collectors.toList());
        List<String> asList = Arrays.asList("a", "b", "c");
        Assert.assertEquals(asList, collected);
    }

    @Test
    public void testMap() {
        List<String> list = new ArrayList<>();
        for (String string : Arrays.asList("a", "b", "helloWorld")) {
            String s = string.toUpperCase();
            list.add(s);
        }
        Assert.assertEquals(Arrays.asList("A", "B", "HELLOWORLD"), list);

        List<String> collect = Stream.of("a", "b")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList("A", "B"), collect);
    }

    @Test
    public void testIsDigit() {
        String s = "123c";
        boolean digits = NumberUtils.isDigits(s);
        log.info("digits is {}", digits);
    }

    @Test
    public void testFilter() {
        List<User> userList = this.userService.findUserList(new User());
        List<User> collect = userList.stream()
                .filter(user -> Character.isDigit(user.getUserName().charAt(user.getUserName().length() - 1)))
                .collect(Collectors.toList());
        log.info("collect is {}", collect);
    }

    //对多个list操作可以使用，多层嵌套循环可以使用，或者嵌套循环多个list
    @Test
    public void testFlatMap() {
        List<User> userList = this.userService.findUserList(new User());
        List<User> users = new ArrayList<User>() {{
            add(new User(8l, "account5", "测试", "pwd", 23, BigDecimal.valueOf(88l)));
        }};
        List<User> collect = Stream.of(userList, users)
                .flatMap(user -> user.stream())
                .collect(Collectors.toList());
        log.info("flatMap list is {}", collect);

        List<String> list = userList.stream()
                .filter(user -> user.getAge() > 10)
                .filter(user -> user.getAge() < 25)
                .map(user -> user.getUserName())
                .collect(Collectors.toList());
        log.info("list is {}",list);
    }

    @Test
    public void testMaxAndMin() {
        List<User> userList = this.userService.findUserList(new User());
        User user1 = userList.stream()
                .min(Comparator.comparing(user -> user.getAge()))
                .get();
        log.info("min user is {}", user1);
        User user2 = userList.stream()
                .max(Comparator.comparing(user -> user.getAge()))
                .get();
        log.info("max user is {}", user2);
    }

    // reduce操作可以实现从一组值中生成一个值，count、max、min
    // reduce方法有两种形式，一种是需要有个初始值，另一种则不需要有初始值
    @Test
    public void testReduce() {
        List<User> userList = this.userService.findUserList(new User());
        int y = 0;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(0).getAge() > userList.get(i).getAge()) {
                y = i;
            }
        }
        log.info("min user is {}", userList.get(y));

        User user = userList.get(0);
        for (User user1 : userList) {
            if (user.getAge() > user1.getAge()) {
                user = user1;
            }
        }
        log.info("min user is {}", user);

        //reduce模式
        User user2 = userList.get(0);
        for (User user3 : userList) {
            //combine
        }

        //使用reduce求和
        int reduce = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        Assert.assertEquals(6, reduce);

//        userList.stream().reduce(reduce)

        int acc = 0;
        for (Integer ele : Arrays.asList(1, 2, 3)) {
            acc += ele;
        }
        Assert.assertEquals(6, acc);
    }

    @Test
    public void testForeach(){
        List<User> userList = this.userService.findUserList(new User());
        List<User> users = new ArrayList<>();
        userList.stream().forEach(user -> {
            if(user.getAge() < 25){
                users.add(user);
            }
        });
        log.info("users is {}",users);

        //可以直接return返回list，省的手动创建list List<User> users = new ArrayList<>();
         /*return userList.stream()
                .filter(user -> user.getAge() > 25)
                .map(user -> user.getUserName())
                .collect(Collectors.toSet());*/

    }

    /**
     * 对基本类型进行特殊处理的方法mapToInt
     */
    @Test
    public void testSummaryStatistics(){
        List<User> userList = this.userService.findUserList(new User());
        IntSummaryStatistics summaryStatistics = userList.stream()
                .mapToInt(user -> user.getAge())
                .summaryStatistics();
        log.info("info === max is {},min is {},avg is {},sum is {},count is {}",summaryStatistics.getMax(),summaryStatistics.getMin(),
                summaryStatistics.getAverage(),summaryStatistics.getSum(),summaryStatistics.getCount());
        log.debug("debug === max is {},min is {},avg is {},sum is {},count is {}",summaryStatistics.getMax(),summaryStatistics.getMin(),
                summaryStatistics.getAverage(),summaryStatistics.getSum(),summaryStatistics.getCount());
    }

    /**
     * optional 用来替换null值
     */
    @Test
    public void testOptional(){
        // 创建某个值的Optional对象
        Optional<String> a = Optional.of("a");
        Assert.assertEquals("a",a.get());
        // empty
        Optional<Object> empty = Optional.empty();
        // ofNullable 可将一个空值替换成Optional对象
        Optional<String> s = Optional.ofNullable("");
        // isParent 表示一个optional对象里是否有值
        boolean present = empty.isPresent();
        Assert.assertFalse(present);
        boolean present1 = a.isPresent();
        Assert.assertTrue(present1);
    }

    @Test
    public void testToValue(){
        List<User> userList = this.userService.findUserList(new User());
        Optional<User> collect = userList.stream().collect(Collectors.maxBy(Comparator.comparing(User::getAge)));
        System.out.println(collect.get());
    }

    @Test
    public void testGroupingBy(){
        List<User> userList = this.userService.findUserList(new User());
        Map<Integer, List<User>> collect = userList.stream().collect(Collectors.groupingBy(User::getAge));
        System.out.println(collect);
        Map<Integer, Long> collect2 = userList.stream().collect(Collectors.groupingBy(User::getAge, Collectors.counting()));
        System.out.println(collect2);
        Map<Integer, List<String>> collect3 = userList.stream()
                .collect(Collectors.groupingBy(User::getAge, Collectors.mapping(User::getUserName, Collectors.toList())));
        System.out.println(collect3);
        String collect1 = userList.stream().map(User::getUserName).collect(Collectors.joining(",","[","]"));
        System.out.println(collect1);

        StringBuilder sb = userList.stream()
                .map(User::getUserName)
                .reduce(new StringBuilder(),
                        (builder, userName) -> {
                            if (builder.length() > 0) {
                                builder.append(",");
                            }
                            builder.append(userName);
                            return builder;
                        }, (builder1, builder2) -> builder1.append(builder2));
        sb.insert(0,"[");
        sb.append("]");
        sb.toString();
        System.out.println(sb);
        /*String string = userList.stream()
                .map(User::getUserName)
                .reduce(new StringCombiner(",", "[", "]"),
                        StringCombiner::add,
                        StringCombiner::merge
                ).toString();*/
    }
}
