package com.primary.example.andalgorithm;

import org.junit.Test;

/**
 * com.primary.example.andalgorithm.AndAlgorithm
 * 与算法
 * Java中的"与"分为逻辑与和位与
 * @author lipeng
 * @date 2019-02-01 16:53
 */
public class AndAlgorithm {

    /**
     * 逻辑与
     * 前后都为true，则返回true
     * 前后有一个为false，则返回false
     */
    @Test
    public void test1() {
        System.out.println("逻辑与逻辑");
        System.out.println(1 == 1 && 2 == 2);
        System.out.println(3 == 3 && 3 == 4);
    }

    @Test
    public void test2() {
        System.out.println("位与运算");
        System.out.println("8的二进制：" + Integer.toBinaryString(8));
        System.out.println("9的二进制：" + Integer.toBinaryString(9));
        System.out.println(8 & 9);
    }
}
