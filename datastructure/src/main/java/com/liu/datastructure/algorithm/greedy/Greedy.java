package com.liu.datastructure.algorithm.greedy;

import java.util.*;

/**
 * @Description: 贪心算法
 * @author: liurunkai
 * @Date: 2020/3/22 14:10
 */
public class Greedy {
    public static void main(String[] args) {
        // 存放所有的电台及电台所覆盖的地区
        Map<String, Set<String>> broadcasts = new HashMap<>();
        Set<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        Set<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        Set<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        Set<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        Set<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("k1",hashSet1);
        broadcasts.put("k2",hashSet2);
        broadcasts.put("k3",hashSet3);
        broadcasts.put("k4",hashSet4);
        broadcasts.put("k5",hashSet5);
        // 存放所有要覆盖的地区
        Set<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        // 存放所有被选中的电台
        List<String> selects = new ArrayList<>();
        // 存放电台所覆盖的地区和所有地区的交集(公共地区)
        Set<String> tempSet = new HashSet<>();
        // 只要allAreas(要覆盖的地区)不为空就说明地区还没有被电台覆盖完，就遍历，因为没选择一个电台，就将其所覆盖的地区从所有地区allAreas里面去重，如果allAreas为空，则说明所有地区被覆盖
        while (allAreas.size() > 0) {
            // 存放覆盖最多未覆盖地区的电台
            String maxKey = null; // 也可以将maxKey定义到while循环外面，但是没开始一次while循环要将maxKey置为null
            // 遍历所有的电台
            for (Map.Entry<String,Set<String>> entry : broadcasts.entrySet()) {
                // 每遍历一个电台 ，就将tempSet清空，也可以将tempSet定义到for循环里面
                tempSet.clear();
                // 将电台所覆盖的地区取出来并存到临时tempSet中
                Set<String> broadcast = entry.getValue();
                tempSet.addAll(broadcast);
                // 取该电台所覆盖的地区和所有要覆盖的地区的交集
                tempSet.retainAll(allAreas); // 求出tempSet和allAreas的交集，并重新赋值给tempSet
                /**
                 * tempSet.size() > 0：该电台所覆盖的地区包含未覆盖的地区
                 * maxKey == null || tempSet.size() > broadcasts.get(maxKey).size()：该电台所覆盖的地区比之前找到的maxKey所覆盖的地区要多，就将maxKey置为当前电台
                 * maxKey == null || tempSet.size() > broadcasts.get(maxKey).size()：体现出了贪心算法：每一步选择中都采取最好或者最优(即最有利)的选择
                 */
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = entry.getKey();
                }
            }
            // 每遍历完一次所有电台，就将覆盖最多地区的电台添加到selects中
            if (maxKey != null) {
                selects.add(maxKey);
                // 将该电台所覆盖的地区从allAreas所有地区里面去除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        selects.stream().forEach(broadcast -> System.out.println(broadcast));
    }
}
