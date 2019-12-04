package com.primary.example.string;

import org.junit.Test;

/**
 * com.primary.example.string.StringTest
 * Java常量池用于保存编译期间已确认，已编译class文件的一部分
 * @author lipeng
 * @date 2019-03-18 17:57
 */
public class StringTest {

    /**
     * 编译期间可以确认
     */
    @Test
    public void test1() {
        String str1 = "123";
        String str2 = "123";
        System.out.println(str1 == str2);
    }

    /**
     * 编译期间可以确认
     */
    @Test
    public void test2() {
        String str1 = "123";
        String str2 = "12" + "3";
        System.out.println(str1 == str2);
    }

    /**
     * 编译期间不可以确认，因为存在变量temp
     */
    @Test
    public void test3() {
        String str1 = "123";
        String temp = "3";
        String str2 = "12" + temp;
        System.out.println(str1 + "=====" + str2);
        System.out.println(str1 == str2);
    }

    /**
     * 编译期间可以确认，变量使用final修饰
     */
    @Test
    public void test4() {
        String str1 = "123";
        final String temp = "3";
        String str2 = "12" + temp;
        System.out.println(str1 == str2);
    }

    /**
     * 编译期间不可以确认，temp变量需要调用方法之后才可以确认
     */
    @Test
    public void test5() {
        String str1 = "123";
        final String temp = getTemp();
        String str2 = "12" + temp;
        System.out.println(str1 == str2);
    }

    public String getTemp() {
        return "3";
    }

    @Test
    public void test6() {
        String str = "smile";
        String str1 = str.intern();
        System.out.println(str == str1);
    }
}
