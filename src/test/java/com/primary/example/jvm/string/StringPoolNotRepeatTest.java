package com.primary.example.jvm.string;

import org.junit.Test;

/**
 * com.primary.example.jvm.string.StringPoolNotRepeatTest
 *
 * 证明常量池中不能有相同的字符串
 * debug模式下查看memory窗口中的java.lang.String对应的Count
 * @author lipeng
 * @date 2021/7/17 9:22 AM
 */
public class StringPoolNotRepeatTest {

    @Test
    public void test() {
        System.out.println();// 3806
        System.out.println("jack");// 3806(该行代码之前有多少个字符串常量)
        System.out.println("lucy");// 3807
        System.out.println("alone");// 3808
        System.out.println("bob");// 3809
        System.out.println("smith");// 3810

        System.out.println("jack");// 3810
        System.out.println("lucy");// 3810
        System.out.println("alone");// 3810
        System.out.println("Carl");// 3810
        System.out.println("bob");// 3811
        System.out.println("smith");// 3811
    }
}
