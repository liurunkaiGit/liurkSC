package com.liu.datastructure.algorithm.kmp;

import java.util.Arrays;

/**
 * @Description: kmp算法
 * @author: liurunkai
 * @Date: 2020/3/14 16:08
 */
public class Kmp {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        int index = kmpSearch(str1, str2, next);
        System.out.println(index);
    }

    /**
     * kmp算法
     *
     * @param str1
     * @param str2
     * @param next
     * @return
     */
    private static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j  >0  && str1.charAt(i) != str2.charAt(j))  {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) { //找到了
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取子串的部分匹配值 表
     *
     * @param str2
     * @return
     */
    private static int[] kmpNext(String str2) {
        int[] next = new int[str2.length()];
        next[0] = 0; //当待匹配的串只有一个字符时，部分匹配值表为0
        for (int i = 1, j = 0; i < str2.length(); i++) {
            // 当str2.charAt(i) != str2.charAt(j)成立时，我们 需要从next[j - 1]获取新的j
            while (j > 0 && str2.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            // 当str2.charAt(i) == str2.charAt(j)满足时，部分匹配值就+1
            if (str2.charAt(i) == str2.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }


}
