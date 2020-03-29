package com.liu.datastructure.algorithm.kmp;

/**
 * @Description: 暴力匹配
 * @author: liurunkai
 * @Date: 2020/3/8 16:46
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "abcdeabcbcdef";
        String str2 = "bcdef1";
        violenceMatch(str1,str2);
    }

    private static void violenceMatch(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
//        System.out.println(chars[0]);
        char[] chars2 = str2.toCharArray();
        int i = 0;
        int j = 0;
        while (i < chars1.length && j < chars2.length) {
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == chars2.length) {
            System.out.println(i - j);
        } else {
            System.out.println(-1);
        }
    }
}
